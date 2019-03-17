/**
 * 
 */
package com.onlinetoolkit.rest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.print.DocFlavor.URL;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

/**
 * @author Santhanam
 *
 */
@Path("/encodedecodeservice")
public class EncodeDecodeService {

	Logger LOGGER = Logger.getLogger(DateService.class.getName());

	@POST
	@Path("/encode")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public Response encodUrls(String jsonString) {

		LOGGER.log(Level.SEVERE, "enter encodUrls service");
		JSONObject resJson = new JSONObject();
		try {
			JSONObject jsonObj = new JSONObject(jsonString);
			resJson.put("EncodedURL", EncodeDecodeUrls(jsonObj.getString("url"), "encode", jsonObj.getString("charset")));
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			LOGGER.log(Level.SEVERE, "leaving encodUrls service");
		}
		return Response.status(201).entity(resJson.toString()).build();

	}

	@POST
	@Path("/decode")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public Response decodeUrls(String jsonString) {

		LOGGER.log(Level.SEVERE, "enter decodeUrls service");
		JSONObject resJson = new JSONObject();
		try {
			JSONObject jsonObj = new JSONObject(jsonString);
			resJson.put("EncodedURL", EncodeDecodeUrls(jsonObj.getString("url"), "decode", jsonObj.getString("charset")));
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			LOGGER.log(Level.SEVERE, "leaving decodeUrls service");
		}
		return Response.status(201).entity(resJson.toString()).build();

	}

	public String EncodeDecodeUrls(String urlString, String type, String chareSet) {

		String urlStr = "";
		if ("".equalsIgnoreCase(urlString) || urlString == null)
			return urlStr;

		try {
			String url = null;
			if ("encode".equalsIgnoreCase(type)) {
				url = URLEncoder.encode(urlString, chareSet);

			} else if ("decode".equalsIgnoreCase(type)) {
				url = URLDecoder.decode(urlString, chareSet);
			}
			return url.toString();
		} catch (UnsupportedEncodingException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
		return urlStr;

	}
}
