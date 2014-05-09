package com.server.learning.services;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.cache.NoCache;

@Path("/authentication")
@NoCache
public interface AuthenticationResource {
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("login")
	public String login(
			@Context HttpServletRequest context,
			@QueryParam("username") String username,
			@QueryParam("password") String password);

}
