package com.tennisfolks.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.mindrot.jbcrypt.BCrypt;

@Path("/test")
public class Test {
	
	@GET
	@Path("/{text}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEncryptedText(@PathParam("text") String text) {
		return Response.ok(text + ": " + BCrypt.hashpw(text, BCrypt.gensalt())).build();
	}

}
