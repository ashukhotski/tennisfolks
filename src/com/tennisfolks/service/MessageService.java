package com.tennisfolks.service;

import java.util.*;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.tennisfolks.dao.MessageDAO;
import com.tennisfolks.dao.UserDAO;
import com.tennisfolks.entities.Message;
import com.tennisfolks.util.UserSessionService;

@Path("/dialogues") @Stateless
public class MessageService {
	
	@EJB
	MessageDAO messageDAO;
	
	@EJB
	UserDAO userDAO;
	
	@EJB
	UserSessionService userSessionService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDialogues(
		@QueryParam("token") String token, 
		@QueryParam("page") int page
	) {
		if (
			userSessionService.getSession().ok() && 
			token.equals(userSessionService.getSession().getToken())
			) {
			int maxResults = 10;
			int startPosition = (maxResults * (page - 1) >= 1) ? maxResults * (page - 1) : 0;
			int size = messageDAO.countDialogues(userSessionService.getSession().getId());
			List<Message> results = messageDAO.getDialogues(userSessionService.getSession().getId(), startPosition, maxResults);

			int currentPage = page;
			int nextPage = currentPage;	
			if (currentPage * maxResults < size) {
				nextPage = nextPage + 1;	
			}
			String response = "{\"currentPage\": " + currentPage + ", \"nextPage\": " + nextPage + ", \"results\": " + (new Gson()).toJson(results) + "}";

			return Response.ok(response, MediaType.APPLICATION_JSON).build();
		} else { return Response.ok("{\"currentPage\": " + 1 + ", \"nextPage\": " + 1 + ", \"results\": []}").build(); }
	}
	
	@GET
	@Path("/{dialogueId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMessages(
		@PathParam("dialogueId") String dialogueId,
		@QueryParam("token") String token, 
		@QueryParam("page") int page
	) {
		if (
			userSessionService.getSession().ok() && 
			token.equals(userSessionService.getSession().getToken())
			) {
			int maxResults = 10;
			int startPosition = (maxResults * (page - 1) >= 1) ? maxResults * (page - 1) : 0;
			int size = messageDAO.countMessages(dialogueId);
			List<Message> results = messageDAO.getMessages(dialogueId, startPosition, maxResults);
			
			for (Message msg : results) {
				if (!msg.isRead() && (msg.getRecipientId() == userSessionService.getSession().getId())) {
					msg.setRead(true);
					msg = messageDAO.update(msg);
				}
			}
			
			int currentPage = page;
			int nextPage = currentPage;	
			if (currentPage * maxResults < size) {
				nextPage = nextPage + 1;	
			}
			String response = "{\"currentPage\": " + currentPage + ", \"nextPage\": " + nextPage + ", \"results\": " + (new Gson()).toJson(results) + "}";

			return Response.ok(response, MediaType.APPLICATION_JSON).build();
		} else { return Response.ok("{\"currentPage\": " + 1 + ", \"nextPage\": " + 1 + ", \"results\": []}").build(); }
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message create(
		Message msg, 
		@QueryParam("token") String token
	) {
		if (
			(msg != null) && 
			userSessionService.getSession().ok() && 
			token.equals(userSessionService.getSession().getToken()) && 
			(msg.getSenderId() == userSessionService.getSession().getId())
			) {
			//com.google.gson.Gson gson = new com.google.gson.GsonBuilder().create();
			//System.out.println("msg json: " + gson.toJson(msg));
			Message message = new Message();
			message.setBody(msg.getBody());
			message.setRecipient(userDAO.find(msg.getRecipientId()));
			message.setSender(userDAO.find(msg.getSenderId()));
			message.setDialogueId(msg.getRecipientId() < msg.getSenderId() ? msg.getRecipientId() + "-" + msg.getSenderId() : msg.getSenderId() + "-" + msg.getRecipientId());
			message.setRead(false);
			message.setDeleted(false);
			return messageDAO.create(message);
		} else { return null; }
	}
	
	@PUT
	@Path("/readmessage")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message read(Message msg, @QueryParam("token") String token) {
		if (
				(msg != null) && 
				userSessionService.getSession().ok() && 
				token.equals(userSessionService.getSession().getToken()) && 
				(msg.getRecipientId() == userSessionService.getSession().getId())
				) {
				Message message = messageDAO.find(msg.getId());
				if (!message.isRead()) {
					message.setRead(true);
					message = messageDAO.update(message);
				}
				return message;
			} else { return null; }
	}

}
