package com.rest.api.get;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class getAPI {

	/**
	 * Rest Assured given(). when(). then(). and()
	 *
	 * logging will be only apply on given ,when,then
	 */
	@Test
	public void getAPICurcuitTest_1() {

		given().log().all().when().log().all().get("http://ergast.com/api/f1/2018/circuits.json").then().log().all()
				.assertThat().body("MRData.CircuitTable.Circuits.circuitId", hasSize(21));
	}

	@Test
	public void getAPICurcuitTest_2() {
		Response response = given().when().get("http://ergast.com/api/f1/2018/circuits.json");
		int status = response.statusCode();
		Assert.assertEquals(status, 200);
		System.out.println(response.prettyPrint());
	}

	@Test
	public void getAPICurcuitTest_Contentlength() {
		RestAssured.baseURI = "http://ergast.com";
		given().log().all().when().log().all().get("/api/f1/2018/circuits.json").then().log().all().assertThat()
				.statusCode(200).and().header("Content-Length", equalTo("4739")).and().contentType(ContentType.JSON);
	}

	@Test
	public void getmd5_json_queryparam() {
		String md5expectedvalue = "098f6bcd4621d373cade4e832627b4f6";
		String paramvalue = "test";
		given().log().all().param("text", paramvalue).when().log().all().get("http://md5.jsontest.com").then().log()
				.all().assertThat().body("md5", equalTo(md5expectedvalue));
	}

	@DataProvider(name = "circuitsandyears")
	public Object[][] Datacircuityear() {
		return new Object[][] { { "2017", 20 }, { "1987", 16 } };
	}

	@Test(dataProvider = "circuitsandyears")
	public void getAPICurcuitTest_circuityear(String circuityear, int circuitcount) {
		int responsecode = 200;
		// http://ergast.com/api/f1/2017/circuits.json
		RestAssured.baseURI = "http://ergast.com";
		given().log().all().pathParam("raceyear", circuityear).when().log().all()
				.get("/api/f1/{raceyear}/circuits.json").then().log().all().assertThat()
				.body("MRData.CircuitTable.Circuits.circuitId", hasSize(circuitcount)).and().statusCode(responsecode)
				.and().contentType(ContentType.JSON);
	}
}