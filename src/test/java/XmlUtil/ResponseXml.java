package XmlUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.w3c.dom.Element;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ResponseXml {
	
	
	@Test
	public void GetUser_HashMap_XMLResponse_withXMLPath()
	{
		RestAssured.baseURI = "https://gorest.co.in";

		RequestSpecification request = RestAssured.given();
		request.header("Authorization", "Bearer  APUtzRLc0jWq7lfg5K4DhT03ERex_Mo5MxGc");
		request.header("Accept","application/xml");
				
		Map<String, String> param = new HashMap<String ,String>();
		param.put("first_name", "john");
		param.put("gender", "male");
		
		request.queryParams(param);

		Response response = request.get("/public-api/users");

		System.out.println(response.statusCode());
		System.out.println(response.prettyPrint());
		System.out.println(response.getHeader("Server"));
		
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(response.getHeader("Server"), "nginx");
		
		
		XmlPath xml=	response.xmlPath();
		String pagevalue = xml.get("response._meta.pageCount");
		System.out.println(pagevalue);
		
		Assert.assertEquals(pagevalue, "1");
			
	}

	@Test
	public void GetUser_HashMap_XMLResponse_withXMLParser()
	{
		RestAssured.baseURI = "https://gorest.co.in";

		RequestSpecification request = RestAssured.given();
		request.header("Authorization", "Bearer  APUtzRLc0jWq7lfg5K4DhT03ERex_Mo5MxGc");
		request.header("Accept","application/xml");
				
		Map<String, String> param = new HashMap<String ,String>();
		param.put("gender", "female");
		
		request.queryParams(param);

		Response response = request.get("/public-api/users");

		System.out.println(response.statusCode());
		String xmlresponse = response.prettyPrint();
		
		
		
		XmlParser xmlxp  = new XmlParser(xmlresponse);
		
	 	String name = xmlxp.getTextContent("//response//_meta//success[text()='true']");
	 	System.out.println("Name is " + name);
	 	
	 	Assert.assertEquals(name, "true");
		
	 	List<Element> listitem =xmlxp.getElements("//response//result//item");
	 	System.out.println(listitem.size());
	 	for(Element e: listitem)
	 	{
	 		String item = e.getTextContent();
	 		System.out.println(item);
	 	}
			
	}
}
