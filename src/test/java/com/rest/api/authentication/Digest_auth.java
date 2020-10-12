package com.rest.api.authentication;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class Digest_auth {
	
	/**
	 * 
	 *  Different types of authentication and how we are using in Rest Assured.
	 * Basic
	 * preepmtive
	 * digest
	 * form
	 * Oauth 1.0
	 * Oauth 2.0
	 * 
	 * 
	 * Digest authentication is secured authentication where username and password are passed in a secure way 
	 * over the network as we are converting username and password in hash codes and then sending over the
	 * network.
	 * 
	 * Basic authentication we dont convert username and password into hashing send directly 
	 * over the network
	 */
	

	@Test
	public void digest_auth_testing()
	{
		RestAssured.baseURI = "http://the-internet.herokuapp.com";
		given().log().all().
		         auth().digest("admin", "admin").
		when().log().all().
		      get("/basic_auth").
		 then().log().all()
		 .assertThat().statusCode(200).and().contentType(ContentType.HTML);
	}

}
