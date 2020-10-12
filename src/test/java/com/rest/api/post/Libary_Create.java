package com.rest.api.post;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class Libary_Create {
	
	@Test
	public void Create_library_Post() throws JsonProcessingException
	{
		LibraryPOJO lp = new LibraryPOJO("Learn API", "abcz", "181", "John foe");
		
	  //Jackson API using ObjectMapper
		
		ObjectMapper obj = new ObjectMapper();
	
		
		String libraryjson = obj.writeValueAsString(lp);
		
		System.out.println(libraryjson);
		
		
		RestAssured.baseURI = "http://216.10.245.166";
		
	ExtractableResponse<Response> response =	given().log().all()
		       .contentType(ContentType.JSON)
		       // .body(new File("./DataFiles/libraryAdd.json"))
		       .body(libraryjson)
		.when().log().all()
		       .post("/Library/Addbook.php")
		 .then().log().all()
		       .assertThat().statusCode(200).extract();
		  //     and().body("Body.ID", equalTo("abcz111"));
		    
		//System.out.println(ID);
		       
	String output = response.asString();
	System.out.println(output);
		    
	}
	
       
}
