import Files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JsonPath js = new JsonPath(Payload.CoursePrice());
		//Print No of courses returned by API
		
		
		int count = js.getInt("courses.size()");
		System.out.println(count);
		
		
		//Print Purchase Amount
		
		int amount = js.getInt("dashboard.purchaseAmount");
		System.out.println(amount);
		
		//Print Title of the first course
		
		String title = js.getString("courses.title[0]");
		System.out.println(title);
		
		//Print All course titles and their respective Prices
		
		System.out.println("Print no of copies sold by RPA Course");
		
		for(int i=0;i<count;i++) 
		{
			String titlesCourse = js.get("courses["+i+"].title");	
			if(titlesCourse.equalsIgnoreCase("RPA"))
			
			{
			int copies = js.get("courses["+i+"].copies");
			 System.out.println(copies);
			 break;
			}
			
			}
			
		}
		
		//Print no of copies sold by RPA Course
		
		//int copies= js.getInt("courses[2].copies");
		//System.out.println(copies);
	
	//Verify if Sum of all Course prices matches with Purchase Amount

		
	}
	
	


