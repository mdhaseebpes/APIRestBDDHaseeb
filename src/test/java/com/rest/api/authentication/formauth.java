package com.rest.api.authentication;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.http.ContentType;

public class formauth {
	
	/**
	 * form based authentications
	 * 
	 * Different types of authentication and how we are using in Rest Assured.
	 * Basic
	 * preepmtive
	 * digest
	 * form
	 * Oauth 1.0
	 * Oauth 2.0
	 */
	
	@Test
	public void form_auth_testing()
	{
		
		given().log().all()
		.auth().
		        form("username", "password", new FormAuthConfig("https://classic.freecrm.com/system/authenticate.cfm", "Username", "Password")).
		when().log().all().
		      get("https://classic.freecrm.com/system/authenticate.cfm").
		 then().log().all()
		 .assertThat().statusCode(200);
	}

}
