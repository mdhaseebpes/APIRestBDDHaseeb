package com.rest.api.authentication;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Basicauth {
	/**
	 * * Different types of authentication and how we are using in Rest Assured.
	 * Basic
	 * preepmtive
	 * digest
	 * form
	 * Oauth 1.0
	 * Oauth 2.0
	 */
	
	@Test
	public void preemptive_basic_auth_testing()
	{
		RestAssured.baseURI = "http://the-internet.herokuapp.com";
		given().log().all().
		         auth().preemptive().basic("admin", "admin").
		when().log().all().
		      get("/basic_auth").
		 then().assertThat().statusCode(200).and().contentType(ContentType.HTML);
	}

	

	@Test
	public void basic_auth_testing()
	{
		RestAssured.baseURI = "http://the-internet.herokuapp.com";
		given().log().all().
		         auth().basic("admin", "admin").
		when().log().all().
		      get("/basic_auth").
		 then().assertThat().statusCode(200).and().contentType(ContentType.HTML);
	}
}
