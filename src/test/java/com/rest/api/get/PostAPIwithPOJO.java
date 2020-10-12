package com.rest.api.get;


import java.io.File;
import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;


public class PostAPIwithPOJO {

	/**
	 * 
	 * Encapsulation - it is object oriented concept >>containing private
	 * members(variables,methods) >> accessed by Public(getter and setter method)
	 * POJO - Plain old Java Object -- Java Class >>containing private
	 * members(variables,methods) >> accessed by Public(getter and setter method)
	 * 
	 * Create a user Post - URL User Java class(POJO) -- JSON Object
	 * 
	 * 
	 * "first_name": "Emm1", "last_name": "Hack1", "gender": "male", "dob":
	 * "1991-08-01", "email": "john3sa6bz3dzbk@gmail.com", "phone": "695-997-0222",
	 * 
	 * .body("{\"first_name\": \"john\",\"last_name\": \"rambo\",\"gender\":
	 * \"male\",\"dob\": \"01-01-1990\",\"email\": \"joqa210@gmail.com\",\"phone\":
	 * \"888-888-12334\",\"website\": \"http://www.labs.com\",\"address\": \"12,new
	 * Avenue,Buckhead,Atlanta\",\"status\": \"active\"}").
	 * 
	 * 
	 * https://gorest.co.in/public-api/users
	 * @throws JsonProcessingException 
	 * 
	 */

	@Test
	public void createUser_with_Pojo_Test() throws JsonProcessingException {
		User user = new User("John", "Rambo", "male", "01-01-1990", "rerneb71@gmail.com", "+888-888-12334",
				"http://www.labs.com", "12 new Avenue Buckhead Atlanta", "active");

		// converting java pojo object to json -- is called as serialization - JACKSON
		// API

		ObjectMapper mapper = new ObjectMapper();
		String 	userJson = mapper.writeValueAsString(user);
		System.out.println(userJson);

	   user.setLast_name("Cena");
				  
		  given().log().all().auth().oauth2("APUtzRLc0jWq7lfg5K4DhT03ERex_Mo5MxGc")
		       //  ("Authorization","Bearer  APUtzRLc0jWq7lfg5K4DhT03ERex_Mo5MxGc")
		         .body(userJson).
		  when().log().all()
		       .post("https://gorest.co.in/public-api/users").
		  then().log().all()
		        .assertThat().statusCode(200).and()
		        .contentType(ContentType.JSON)
		     
		       .body("result.first_name", equalTo(user.getFirst_name()));
	}

}
