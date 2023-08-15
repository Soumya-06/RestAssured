package Files;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {
	
	@Test
	
	public void AddBook() {
		
	RestAssured.baseURI = "http://216.10.245.166" ;
	String response = given().log().all().header("Content-Type", "application/json").body("Payload.Addbook()")
	.when().post("/Library/Addbook.php")
	.then().assertThat().log().all().statusCode(200).extract().response().asString();
	JsonPath js = ReUsuableMethods.rawToJson(response);	
	String id = js.getString("ID");	
	System.out.println(id);
		
		
		
	}

	
	}
	

	
	
	

