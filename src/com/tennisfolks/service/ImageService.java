package com.tennisfolks.service;

import java.io.*;
import java.net.URLConnection;
import java.util.*;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import com.tennisfolks.dao.ImageDAO;
import com.tennisfolks.entities.Image;
import com.tennisfolks.util.UserSessionService;

import sun.misc.IOUtils;

@SuppressWarnings("restriction")
@Path("/images") @Stateless
public class ImageService {
	
	@EJB
	ImageDAO imageDAO;
	
	@EJB
	UserSessionService userSessionService;
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Image getImageBase64Encoded(@PathParam("id") int id) {
		return imageDAO.find(id);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") int id, @QueryParam("token") String token) {
		if (
			userSessionService.getSession().ok() && 
			userSessionService.getSession().getToken().equals(token)
		) {
			Image image = imageDAO.find(id);
			if ((image != null) && (image.getUserId() == userSessionService.getSession().getId())) {
				imageDAO.delete(image);
				return Response.ok("Image has been deleted").build();
			}
		}
		return Response.ok("Image has not been deleted").build();
	}
	
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Image create(
			MultipartFormDataInput data, 
			@QueryParam("token") String token) {
		if (
			userSessionService.getSession().ok() && 
			userSessionService.getSession().getToken().equals(token)
		) {	
			Map<String, List<InputPart>> formParts = data.getFormDataMap();
			List<InputPart> inPart = formParts.get("file");
			
			String fileName = "";
			String mimetype = "";
			InputStream inputStream = null;
			for (InputPart inputPart : inPart) {
				try {
					MultivaluedMap<String, String> headers = inputPart.getHeaders();
					fileName = parseFileName(headers);
					
					inputStream = inputPart.getBody(InputStream.class, null);
					mimetype = URLConnection.getFileNameMap().getContentTypeFor(fileName);
				} catch (Exception e) { }
			}

			if (inputStream == null) { return null; }
			try {
				Image image = new Image();
				image.setName(fileName);
				image.setContent(IOUtils.readFully(inputStream, -1, true));
				image.setMimetype(mimetype);
				image.setUserId(userSessionService.getSession().getId());
				return imageDAO.create(image);
			} catch (Exception e) { return null; }
		} else { return null; }
	}
	
	private String parseFileName(MultivaluedMap<String, String> headers) {
		String[] contentDispositionHeader = headers.getFirst("Content-Disposition").split(";");
		for (String name : contentDispositionHeader) {
			if ((name.trim().startsWith("filename"))) {
				String[] tmp = name.split("=");
				String fileName = tmp[1].trim().replaceAll("\"","");
				return fileName;
			}
		}
		return "";
	}

}
