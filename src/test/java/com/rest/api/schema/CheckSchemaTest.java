package com.rest.api.schema;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.matcher.RestAssuredMatchers.matchesXsdInClasspath;

import java.io.File;


public class CheckSchemaTest {
	
	/**
	 * JSON TO JSON SCHEMA
	 * https://www.liquid-technologies.com/online-json-to-schema-converter
	 */
	
	@Test
	public void BookingSchemaTest()
	{
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		given().log().all()
		   .contentType(ContentType.JSON)
	      .body(new File("./DataFiles/creatbookdata.json"))
	   .when().log().all().post("/booking").
	   then().log().all().
	              assertThat().and().
	    	      body(matchesJsonSchemaInClasspath("BookingSchema.json"));
	}
	
	@Test
	public void get_Gorest_userSchemaTest()
	{
		RestAssured.baseURI = "https://gorest.co.in";
		
		given().log().all().
		        auth().oauth2("APUtzRLc0jWq7lfg5K4DhT03ERex_Mo5MxGc")
		       .queryParam("first_name", "Ramon")
		       .queryParam("gender", "male").
		 when().log().all()
		        .get("/public-api/users").
		 then().log().all()
		       .assertThat().statusCode(200).and()
		       .body(matchesJsonSchemaInClasspath("gorestuserschema.json"));
		       
	}
	
	
	 @Test
	 public void testSchemaXml() {
	        given().log().all().
	                contentType("application/xml").
	                header("Accept", "application/xml").
	        when().log().all().
	                get("C:\\Users\\mohammedk\\Desktop\\Medication History\\Response_rx history question\\H Patients Request and Responses V3.0.2\\H 2\\Responses\\H2PBMXresponse.html").
	        then().log().all().
	        body(matchesXsdInClasspath("RxHistoryResponseSurescriptstoProvider.xsd"));
	    }

}
