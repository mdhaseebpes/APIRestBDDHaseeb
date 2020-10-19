package com.rest.api.post;

import java.io.File;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JsonPath js = new JsonPath(new File("./DataFiles/course.json"));

		// Print no of courses returned by API

		int count = js.getInt("courses.size()");
		System.out.println(count);

		// Print purchase amount
		int purchase = js.getInt("dashboard.purchaseAmount");
		System.out.println(purchase);

		// First course
		String course1 = js.get("courses[0].title");
		System.out.println(course1);

		// title course and price amount
		for (int i = 0; i < count; i++) {
			System.out.println(js.get("courses[" + i + "].title").toString());
			System.out.println(js.get("courses[" + i + "].price").toString());
		}

		// copies sold for course

		for (int j = 0; j < count; j++) {
			String titlecourse = js.get("courses[" + j + "].title");
			if (titlecourse.equalsIgnoreCase("RPA")) {
				int copies = js.get("courses[" + j + "].copies");
				System.out.println("Copies sold for RPA " + copies);
			}
		}
		int sum = 0;
		// title course and price amount
		for (int i = 0; i < count; i++) {

			int price = js.getInt("courses[" + i + "].price");
			int copies = js.getInt("courses[" + i + "].copies");
			int amount = price * copies;
			System.out.println(amount);
			sum = sum + amount;

		}
		System.out.println("**************");
		System.out.println(sum);
		Assert.assertEquals(sum, js.getInt("dashboard.purchaseAmount"));
	}

}
