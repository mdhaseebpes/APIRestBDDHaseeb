package com.rest.api.post;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class DeleteAPITest {
	
	
	@Test
	public void Create_Delete_Validate() throws JsonProcessingException {
		
		
		LibraryPOJO lp = new LibraryPOJO("Learn API", "abcz", "040", "John foe");
		
		  //Jackson API using ObjectMapper
			
			ObjectMapper obj = new ObjectMapper();
		
			
			String libraryjson = obj.writeValueAsString(lp);
			
			System.out.println(libraryjson);
			
			
			RestAssured.baseURI = "http://216.10.245.166";
			
			given().log().all()
			       .contentType(ContentType.JSON)
			       // .body(new File("./DataFiles/libraryAdd.json"))
			       .body(libraryjson)
			.when().log().all()
			       .post("/Library/Addbook.php")
			 .then()
			       .assertThat().statusCode(200).extract();
			
			//System.out.println(BookID);
			          
		String actualBookID =lp.getIsbn()+lp.getAisle();
		System.out.println(actualBookID);
							
					
			//Delete
			given().log().all()
		       // .body(new File("./DataFiles/libraryAdd.json"))
		       .body("{\"ID\" :\""+actualBookID+"\"}")
		.when().log().all()
		       .delete("/Library/DeleteBook.php")
		 .then().log().all()
		       .assertThat().statusCode(200);	     
		
	}
	

}
