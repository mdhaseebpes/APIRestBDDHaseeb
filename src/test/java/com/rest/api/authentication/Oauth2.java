package com.rest.api.authentication;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class Oauth2 {
	/**
	 *  Different types of authentication and how we are using in Rest Assured.
	 * Basic
	 * preepmtive
	 * digest
	 * form
	 * Oauth 1.0
	 * Oauth 2.0
	 */
	
	@Test
	public void oauth2_test()
	{
		RestAssured.baseURI= "https://gorest.co.in";
		
		given().log().all().
		       queryParam("first_name", "Ramon").
		       queryParam("gender", "male").
		       auth().oauth2("APUtzRLc0jWq7lfg5K4DhT03ERex_Mo5MxGc").
		when().log().all().
		       get("/public-api/users").
		then().assertThat().statusCode(200).
		       and().contentType(ContentType.JSON);
	}
	
	
	@Test
	public void oauth2_test_authenticationheader()
	{
		RestAssured.baseURI= "https://gorest.co.in";
		
		given().log().all().
		       param("first_name", "Ramon").param("gender", "male").
		       header("Authorization", "Bearer Token_ APUtzRLc0jWq7lfg5K4DhT03ERex_Mo5MxGc").		   
		when().log().all().
		       get("/public-api/users").
		then().assertThat().statusCode(200).
		       and().contentType(ContentType.JSON);
	}

	/**
	 * if we are using header:we need to append with Bearer Keyword along with access token
	 *  header("Authorization","Bearer 784573fa8aee33703c03b734b1d6a467b0f2fec4");
		if we using oauth2 does not need to add Bearer keyword while passing access token
		auth().oauth2("784573fa8aee33703c03b734b1d6a467b0f2fec4");
	 */
	
	@Test
	public void oauth2_post_coop_oauth2()
	{
		RequestSpecification restoauth =given().log().all().
		 auth().oauth2("784573fa8aee33703c03b734b1d6a467b0f2fec4");
		
		Response response = restoauth.post("http://coop.apps.symfonycasts.com/api/675/chickens-feed");
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
	}
	
	@Test
	public void oauth2_post_coop_header()
	{
		RequestSpecification restoauth =given().log().all().
		 header("Authorization","Bearer 784573fa8aee33703c03b734b1d6a467b0f2fec4");
		
		Response response = restoauth.post("http://coop.apps.symfonycasts.com/api/675/chickens-feed");
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
	}
	
	/**
	 * Generate a token at the run time by using token api
	 * Get String tokenID from the response
	 * hit the next API with this tokenID
	 */
	@Test
	public void getAuth2TokenAPITest()
	{
		
		RequestSpecification request= given().log().all()
		       .formParam("client_id", "HappyChicken")
		       .formParam("client_secret", "6abfb748bcde8725d3320ae35f31c687")
		       .formParam("grant_type", "client_credentials");
		
	 Response response=	request.post("http://coop.apps.symfonycasts.com/token");
	 
	 System.out.println(response.statusCode());
	 System.out.println(response.prettyPrint());
	 
	 String tokenID = response.jsonPath().getString("access_token");
	 
	 //http://coop.apps.symfonycasts.com/api/675/chickens-feed 
	 RestAssured.baseURI = "http://coop.apps.symfonycasts.com";
	   given().log().all()
	         .auth().oauth2(tokenID)
	   .when().log().all().post("/api/675/chickens-feed")
	   .then()
	          .assertThat().and().statusCode(200)
	          .contentType(ContentType.JSON)
	          .body("message", equalTo("Your chickens are now full and happy"));
	 
	}
}
