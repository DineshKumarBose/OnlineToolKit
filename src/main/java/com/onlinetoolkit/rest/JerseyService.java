package com.onlinetoolkit.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/message")
public class JerseyService {
	  
	@GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getJSON() {
		JSONObject test = new JSONObject();
        test.put("name", "kumar");
        return test.toString();
    }
    
    @GET
    @Path("/{name}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getByName(@PathParam("name") String msg) {
        String output = "Hello, " + msg + "!";
        JSONObject test = new JSONObject();
        test.put("name", "dinesh");
        return Response.status(200).entity(test.toString()).build();
    }
    
}


