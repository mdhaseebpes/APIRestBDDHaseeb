package com.rest.api.get;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class PostAPI extends responseSpecificationBuilderTest{
	
	/**
	 * 
	 * Default value of String is null, int is 0 ,double is 0.0
	 * 
	 * https://restful-booker.herokuapp.com/apidoc/index.html
	 */
	
	@Test
	public void PostTest() {
		
		 given().log().all()
		       .contentType(ContentType.JSON)
		      .body("{\"username\":\"admin\",\"password\" :\"password123\"}").
		   when().log().all().post("https://restful-booker.herokuapp.com/auth").
		   then().log().all().
		         assertThat().statusCode(200);
	}
	
	
	@Test
	public void PostTest_File() {
		
		String tokenID= given().log().all()
		       .contentType(ContentType.JSON)
		      .body(new File("./DataFiles/credentials.json"))
		   .when().log().all().post("https://restful-booker.herokuapp.com/auth").
		   then().log().all().
		         extract().path("token");
		
		System.out.println(tokenID);
		Assert.assertNotNull(tokenID);
	}
	
	@Test
	public void CreateBook_Post()
	{
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		given().log().all()
	       .contentType(ContentType.JSON)
	      .body(new File("./DataFiles/creatbookdata.json"))
	   .when().log().all().post("/booking").
	   then().log().all().
	              assertThat().and().
	              body("booking.totalprice",equalTo(111));
    
	}

}
