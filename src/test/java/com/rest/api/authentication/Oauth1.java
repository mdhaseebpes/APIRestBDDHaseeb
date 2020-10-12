package com.rest.api.authentication;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Oauth1 {
	/**
	 *  Different types of authentication and how we are using in Rest Assured.
	 * Basic
	 * preepmtive
	 * digest
	 * form
	 * Oauth 1.0
	 * Oauth 2.0
	 * 
	 * https://github.com/rest-assured/rest-assured/wiki/Usage
	 * 
	 * In order to use OAuth 1 and OAuth 2 (for query parameter signing) you need to add 
	 * Scribe to your classpath (if you're using version 2.1.0 or older of REST Assured 
	 * then please refer to the legacy documentation). In Maven you can simply add the following dependency
	 * 
	 * we need to pass below parameters for Oauth 1.0
	 * Consumer Key   - 0QV0YNltMtlvyvRWZYOUsW5Gt
       Consumer Secret - S6Cd3gDMF8kLBAUaGcFRyeuKiwxcUfdZuWa7gDm7sNOERGJ4Mb
       Access Token - 1163812759680634880-p8SdY7zQZWYHMpPWtA5msrGtqICN3Y
       Token Secret  - TIDkIR75yRp9HySObi3kdqa2iyylHwNgzvgREjHAn6wre
	 */

	@Test
	public void Oauth1_APITest()
	{
		String Consumer_Key = "0QV0YNltMtlvyvRWZYOUsW5Gt";
		String  Consumer_Secret = "S6Cd3gDMF8kLBAUaGcFRyeuKiwxcUfdZuWa7gDm7sNOERGJ4Mb";
		String Access_Token = "1163812759680634880-p8SdY7zQZWYHMpPWtA5msrGtqICN3Y";
		String Token_Secret = "TIDkIR75yRp9HySObi3kdqa2iyylHwNgzvgREjHAn6wre";
		
		RestAssured.baseURI = "https://api.twitter.com";
		
	RequestSpecification request =	given().log().all()
		       .auth().oauth(Consumer_Key, Consumer_Secret, Access_Token,Token_Secret);
	
	    request.post("/1.1/statuses/update.json?status=Hi!")
	    .then().log().all()
	           .assertThat().statusCode(200).and()
	    	   .contentType(ContentType.JSON).and() 
	           .body("user.name", equalTo("mohammed Haseeb ali khan"));
	     
	
	}
}
