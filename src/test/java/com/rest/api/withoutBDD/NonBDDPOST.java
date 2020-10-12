package com.rest.api.withoutBDD;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class NonBDDPOST {
	
	/**
	 * Request
	 * Hit the API
	 * 
	 */
	
	@Test
	public void Post_NonBDD()
	{
		RestAssured.baseURI = "https://api.twitter.com";
		
		
		RequestSpecification request = RestAssured.given()
		           .auth().oauth("0QV0YNltMtlvyvRWZYOUsW5Gt", "S6Cd3gDMF8kLBAUaGcFRyeuKiwxcUfdZuWa7gDm7sNOERGJ4Mb", "1163812759680634880-o2d0rS4Lbsm9NqnXa79OdHgu5Chp8T", "1wBvfVModwlQrWcbHEAy4qCB0kKTF7e0dvj5p0wmAfAEj");
		
		Response response = request.post("/1.1/statuses/update.json?status=Hi All!");
		
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
		System.out.println(response.getHeader("Server"));
	}

}
