package com.rest.api.get;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;



public class responseSpecificationBuilderTest {
	
	/**
	 * ResponsespecBuilder is used were we have common validation across the testscripts
	 * instead of duplicating the test steps in every test scripts we can use ResponsespecBuilder class and 
	 * ResponseSpecification interface and add the common validation and use in every test script
	 * @return 
	 */
	public static ResponseSpecification test() {
	 ResponseSpecBuilder resB = new ResponseSpecBuilder();
	 ResponseSpecification resSpec_200_OK = resB.expectStatusCode(200).
			                          expectContentType(ContentType.JSON).
			                         // expectHeader("Server", "nginx").
			                          build();
	 return resSpec_200_OK;
	}
	
	 ResponseSpecBuilder resB1 = new ResponseSpecBuilder();
	 ResponseSpecification resSpec_404_BADREQUEST = resB1.expectStatusCode(404).
			                 			            expectHeader("Server", "nginx").build();
	 
	 ResponseSpecBuilder resB2 = new ResponseSpecBuilder();
	 ResponseSpecification resSpec_401_AUTH_FAILURE = resB2.expectStatusCode(401).
                                                      expectHeader("Server", "nginx").build();
	 
	 
	 @Test
	 public void responsespecificationTest()
	 {
		 
		 given().log().all()
		        .header("Authorization", "Bearer  APUtzRLc0jWq7lfg5K4DhT03ERex_Mo5MxGc")
		 .when().log().all()
		 .get("https://gorest.co.in/public-api/users").
		  then().spec(responseSpecificationBuilderTest.test());
	 }
	 
	 
	 @Test
	 public void responsespecificationTest__404_BADREQUEST()
	 {
		 
		 given().log().all()
		        .header("Authorization", "Bearer  APUtzRLc0jWq7lfg5K4DhT03ERex_Mo5MxGc")
		 .when().log().all()
		 .get("https://gorest.co.in/public-api/us").
		  then().spec(resSpec_404_BADREQUEST);
	 }
	 
	 
	 @Test
	 public void resSpec_401_AUTH_FAILURE()
	 {
		 
		 given().log().all()
		        .header("Authorization", "Bearer  APUtzRLc0jWq7lfg5K4DhT03ERex_Mo5MxGc1111")
		 .when().log().all()
		 .get("https://gorest.co.in/public-api/users").
		  then().spec(resSpec_401_AUTH_FAILURE);
	 }
	 

}
