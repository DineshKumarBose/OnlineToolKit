package com.onlinetoolkit.rest;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/dateservice")
public class DateService {

	Logger LOGGER = Logger.getLogger(DateService.class.getName());
	
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
    @Produces({MediaType.TEXT_PLAIN})
	public Response timeStampToDate(String milliseconds) {
    	String date = "";
    	try {
    		Date dateObj = new Date(Long.valueOf(milliseconds));
    		date = dateObj.toString();
    	} catch(Exception e) {
    		LOGGER.log(Level.SEVERE, e.getMessage(), e);
    		date = e.getMessage();
    	}
		return Response.status(201).entity(date).build();

	}
	
}
