package com.onlinetoolkit.rest;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/jsonformatter")
public class JSONFormatter {

	Logger LOGGER = Logger.getLogger(JSONFormatter.class.getName());
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
	public Response createTrackInJSON(String test) {
    	String indented = "";
    	try {
		ObjectMapper mapper = new ObjectMapper();
		Object json = mapper.readValue(test, Object.class);
		indented = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
    	} catch(Exception e) {
    		LOGGER.log(Level.SEVERE, e.getMessage(), e);
    		indented = e.getMessage();
    	}
		return Response.status(201).entity(indented.toString()).build();

	}
	
}
