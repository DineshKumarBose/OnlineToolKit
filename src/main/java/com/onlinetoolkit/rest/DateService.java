package com.onlinetoolkit.rest;

import java.util.Set;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.json.JSONArray;
import org.json.JSONObject;

@Path("/dateservice")
public class DateService {

	Logger LOGGER = Logger.getLogger(DateService.class.getName());
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
	public Response timeStampToDate(String jsonString) {
    	JSONObject resJson = new JSONObject();
    	try {
    		JSONObject jsonObj = new JSONObject(jsonString);
    		DateTime dateObj = new DateTime(Long.valueOf(jsonObj.getString("milliseconds")), DateTimeZone.UTC);
    		resJson.put("UTC_date", dateObj.toString("E, dd MMM yyyy HH:mm:ss z z"));
    		dateObj = new DateTime(Long.valueOf(jsonObj.getString("milliseconds")), DateTimeZone.forTimeZone(TimeZone.getTimeZone(jsonObj.getString("local_time_zone"))));
    		resJson.put("local_date", dateObj.toString("E, dd MMM yyyy HH:mm:ss z Z"));
    		dateObj = new DateTime(Long.valueOf(jsonObj.getString("milliseconds")), DateTimeZone.forTimeZone(TimeZone.getTimeZone(jsonObj.getString("user_time_zone"))));
    		resJson.put("user_date", dateObj.toString("E, dd MMM yyyy HH:mm:ss z Z"));
    	} catch(Exception e) {
    		LOGGER.log(Level.SEVERE, e.getMessage(), e);
    	}
		return Response.status(201).entity(resJson.toString()).build();

	}
	
	@GET
	@Path("/timezone")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getTimeZone() {
		JSONObject resJson = new JSONObject();
		try {
			Set<String> ids = DateTimeZone.getAvailableIDs();
			JSONArray timeZone = new JSONArray();
			for (String id : ids) {
				timeZone.put(id);
			}
			resJson.put("time_zone", timeZone);
		} catch(Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
		return Response.status(201).entity(resJson.toString()).build();
	}
	
}
