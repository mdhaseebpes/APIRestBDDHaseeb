package com.rest.api.authentication;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class Preemptive_auth {
	
	/**
	 * Different types of authentication and how we are using in Rest Assured.
	 * Basic
	 * preepmtive
	 * digest
	 * form
	 * Oauth 1.0
	 * Oauth 2.0
	 * 
	 *  

       Returns the preemptive authentication view. This means that the authentication details are sent in the
        requestheader regardless if the server has challenged for authentication or not.
        Returns:The preemptive authentication specification.
	 */
	
	@Test
	public void Preemptive_basic_auth_testing()
	{
		RestAssured.baseURI = "http://the-internet.herokuapp.com";
		given().log().all().
		         auth().preemptive().basic("admin", "admin").
		when().log().all().
		      get("/basic_auth").
		 then().assertThat().statusCode(200).and().contentType(ContentType.HTML);
	}
}
