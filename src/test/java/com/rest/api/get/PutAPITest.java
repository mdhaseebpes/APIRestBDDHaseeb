package com.rest.api.get;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

import io.restassured.RestAssured;

public class PutAPITest {

	@Test
	public void CreateUser_UpdateUserData() {
		User user = new User("John", "Rambo", "male", "01-01-1990", "rambo71@gmail.com", "+888-888-12334",
				"http://www.labs.com", "12 new Avenue Buckhead Atlanta", "active");

		// converting java pojo object to json -- is called as serialization - using JACKSON API
		
    	ObjectMapper mapper = new ObjectMapper();
		String userJson = null;
		try {
			userJson = mapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(userJson);
		
		RestAssured.baseURI="https://gorest.co.in";
		
		//Create an user
		String UserID =  given().log().all().
		         header("Authorization","Bearer  APUtzRLc0jWq7lfg5K4DhT03ERex_Mo5MxGc")
		         .body(userJson).
		  when().log().all()
		       .post("/public-api/users").
		  then().log().all().spec(responseSpecificationBuilderTest.test())
		        .assertThat().statusCode(200)
		        .extract().path("result.id");
		
		System.out.println("User created ID " + UserID);
		
		//Changing the emailID
		user.setEmail("rambo90@gmail.com");
		
		// converting java pojo object to json -- is called as serialization - using JACKSON API
		String UpdateduserJson = null;
		try {
			UpdateduserJson = mapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(UpdateduserJson);
		
		//Update the user
				 given().log().all().
		         header("Authorization","Bearer  APUtzRLc0jWq7lfg5K4DhT03ERex_Mo5MxGc")
		         .body(UpdateduserJson).
		  when().log().all()
		       .put("/public-api/users"+UserID).
		  then().log().all()
		        .assertThat().statusCode(200).and().
		        body("result.email",equalTo(user.getEmail())).and().
		        body("result.id",equalTo(UserID)).and().
		        body("result.first_name",equalTo(user.getFirst_name()));
				 
				 
				 //Get the updated user id
				 
				 given().log().all().
		         header("Authorization","Bearer  APUtzRLc0jWq7lfg5K4DhT03ERex_Mo5MxGc").
		         queryParam("first_name", user.getFirst_name()).
		         queryParam("id",UserID).
		  when().log().all()
		       .get("/public-api/users").
		  then().log().all()
		        .assertThat().statusCode(200);

	}
}
