package com.server.learning.services;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import com.server.learning.form.FileUploadForm;

@Path("/learning")
public interface LearningResource {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	//@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Path("uploadTugas")
	public String uploadTugas(@Context HttpServletRequest context,
			@MultipartForm MultipartFormDataInput dataInput, 
			@QueryParam("idSiswa") String idSiswa, 
			@QueryParam("idTugas") String idTugas);
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Path("uploadTugas2")
	public String uploadTugas2(@Context HttpServletRequest context, @MultipartForm FileUploadForm form);
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("cobaDao")
	public String testBabDao(@Context HttpServletRequest context);
	
}
