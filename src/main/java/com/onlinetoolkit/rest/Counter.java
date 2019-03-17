package com.onlinetoolkit.rest;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import com.onlinetoolkit.util.UtilMethods;

@Path("/counter")
public class Counter {
	
	Logger LOGGER = Logger.getLogger(Counter.class.getName());
	
	@POST
	@Path("/sentence")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public Response decodeUrls(String jsonString) {

		LOGGER.log(Level.SEVERE, "enter decodeUrls service");
		JSONObject resJson = new JSONObject();
		try {
			JSONObject jsonObj = new JSONObject(jsonString);
			String input_string = jsonObj.getString("input_string");
			int word_count = UtilMethods.countWordsUsingStringTokenizer(input_string.trim());
			int char_count = input_string.length();
			resJson.put("word_count", word_count);
			resJson.put("char_count", char_count);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			LOGGER.log(Level.SEVERE, "leaving decodeUrls service");
		}
		return Response.status(201).entity(resJson.toString()).build();

	}

}
