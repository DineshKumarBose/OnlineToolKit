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

@Path("/comparelist")
public class CompareList {

	Logger LOGGER = Logger.getLogger(CompareList.class.getName());
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
	public Response createTrackInJSON(String jsonString) {
    	JSONObject responseJson = new JSONObject();
	try {
		JSONObject json = new JSONObject(jsonString);
		String list1 = json.getString("list1");
		String list2 = json.getString("list2");
		responseJson.put("resultlist1", list1);
		responseJson.put("resultlist2", list2);
		responseJson.put("onlylist1", UtilMethods.getDiff(list1, list2));
		responseJson.put("onlylist2", UtilMethods.getDiff(list2, list1));
		responseJson.put("commonlist", UtilMethods.getDuplicatesBetweenTwoList(list2, list1));
		responseJson.put("oneortwo", UtilMethods.getAll(list2, list1));
    	} catch(Exception e) {
    		LOGGER.log(Level.SEVERE, e.getMessage(), e);
    	}
		return Response.status(201).entity(responseJson.toString()).build();

	}
	
}
