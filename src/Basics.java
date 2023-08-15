import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import Files.Payload;
import Files.ReUsuableMethods;

public class Basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//Validate if Add Place API is working as expected 
		
		/*given(input all details), 
		when(Submit the API), -- resource and http method
		then(validate the response)
		*/
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").headers("Content-Type", "application/json")
		.body(Payload.addPlace()).when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
	
		System.out.println(response);
		
		JsonPath js=new JsonPath(response); //for parsing json --> value stored in js
		String placeId= js.getString("place_id");
		
		System.out.println(placeId);
		
		
		//Update place
		
		String newAddress = "Summer walk, India";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body("{\n"
				+ "\"place_id\":\""+placeId+"\",\n"
				+ "\"address\":\""+newAddress+"\",\n"
				+ "\"key\":\"qaclick123\"\n"
				+ "}")
		.when().put("/maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg",equalTo("Address successfully updated"));
		
		
		//get Place
		
		
		String getPlaceAddress = given().log().all().queryParam("key", "qaclick123")
				.queryParam("place_id", placeId)
		.when().get("/maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
		
		JsonPath js1= ReUsuableMethods.rawToJson(response);
		String actualAddress = js1.getString("address");
		
		System.out.println(actualAddress);
		
		Assert.assertEquals(actualAddress, newAddress);
		//Add PLace --> Update PLace with new address --> Get place to validate the new address
		
		//cucumber Junit, TestNG
	}

}
