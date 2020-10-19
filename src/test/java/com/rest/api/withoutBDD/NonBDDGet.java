package com.rest.api.withoutBDD;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class NonBDDGet {
	
	/**
	 * HashMap can store null values and null key
	 * HashMap is always non synchronized(multiple threads can access at a time)
	 */

	@Test
	public void Get_NonBDD() {

		RestAssured.baseURI = "https://gorest.co.in";

		RequestSpecification request = RestAssured.given();
		request.header("Authorization", "Bearer  APUtzRLc0jWq7lfg5K4DhT03ERex_Mo5MxGc");
		request.queryParam("first_name", "john");
		request.queryParam("gender", "male");

		Response response = request.get("/public-api/users");

		System.out.println(response.statusCode());
		System.out.println(response.prettyPrint());
		System.out.println(response.getHeader("Server"));
		
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(response.getHeader("Server"), "nginx");
		

	}

	

	@Test
	public void Get_HashMap_withqueyparam()
	{
		RestAssured.baseURI = "https://gorest.co.in";

		RequestSpecification request = RestAssured.given();
		request.header("Authorization", "Bearer  APUtzRLc0jWq7lfg5K4DhT03ERex_Mo5MxGc");
				
		Map<String, String> param = new HashMap<String ,String>();
		param.put("first_name", "john");
		param.put("gender", "male");
		
		request.queryParams(param);

		Response response = request.get("/public-api/users");

		System.out.println(response.statusCode());
		System.out.println(response.prettyPrint());
		System.out.println(response.getHeader("Server"));
		
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(response.getHeader("Server"), "nginx");
		
		
			JsonPath js = response.jsonPath();
		System.out.println(js.getString("_meta"));
	     String s1 = js.getString("_meta.totalCount");
		
		System.out.println(s1);
		Assert.assertEquals(s1,"6");
		
		
		
	}
}
