package RestAPI;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RESTCalls {

	@Test(priority=0)
	public void Register_Success_For_Single_User()
	{
		RestAssured.baseURI = "https://reqres.in";
		RequestSpecification request = RestAssured.given(); 
		request.header("Content-Type", "application/json");
		
		//registration for single user by single JSONObject
		JSONObject json = new JSONObject();
		json.put("email", "User3@icici");
		json.put("password", "Test@654");
	
		request.body(json.toJSONString());
		System.out.println("payload for this call is: "+json.toJSONString());
		
		Response resp = request.post("/api/register");
		int respCode = resp.getStatusCode();
		System.out.println("Response Code for Register Success POST Call is: "+respCode);
		Assert.assertEquals(201, respCode);
		String responseBody1 = resp.getBody().asString();
		System.out.println("Response of successful registration : "+responseBody1);
		
		JsonPath jsonPathEvaluator = resp.jsonPath();
		String token = jsonPathEvaluator.get("token");
		System.out.println("Token from successful registration call: "+token);
		
		Assert.assertTrue("Registration is not Successful", token.equals("QpwL5tke4Pnpja7X"));
		//Assert.assertEquals("Registration Successful", token, "QpwL5tke4Pnpja7X");	
	} 
	
	
	@Test(priority=1)
	public void Register_Success_For_Multiple_Users() throws IOException, ParseException
	{
		RestAssured.baseURI = "https://reqres.in";
		RequestSpecification request = RestAssured.given(); 
		request.header("Content-Type", "application/json");
		
		JSONParser parser = new JSONParser();
		Object obj;
		JSONObject jsonObj;
		
        try {     
            obj = parser.parse(new FileReader("C:\\Gopal\\users.json"));
            JSONArray arr = (JSONArray) obj;
            System.out.println("Given JSONArray is: "+arr.toJSONString());
                        
        	for(int i=0;i<arr.size();i++)
        	{
        		jsonObj = (JSONObject)arr.get(i);
        		System.out.println("payload for this call is: "+jsonObj.toJSONString());
        		request.body(jsonObj.toJSONString());
        		Response resp1 = request.post("/api/register");
        		int respCode1 = resp1.getStatusCode();
        		System.out.println("Response Code for Register Success POST Call is: "+respCode1);
        		Assert.assertEquals(201, respCode1);
        		String responseBody1 = resp1.getBody().asString();
        		System.out.println("Response of successful registration : "+responseBody1);
        		
        		JsonPath jsonPathEvaluator1 = resp1.jsonPath();
        		String token1 = jsonPathEvaluator1.get("token");
        		System.out.println("Token from successful registration call: "+token1);
        		
        		Assert.assertTrue("Registration is not Successful", token1.equals("QpwL5tke4Pnpja7X"));
        		System.out.println("-----------------------------------------------------------------------");
        	}     	
        }
        catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test(priority=2)
	public void Create_Single_User()
	{
		RestAssured.baseURI = "https://reqres.in";
		RequestSpecification request = RestAssured.given(); 
		request.header("Content-Type", "application/json");
		
		//create single user by single JSONObject
		JSONObject json = new JSONObject();
		json.put("name", "Jack");
		json.put("job", "Assistant");
		
		request.body(json.toJSONString());
		Response resp = request.post("/api/users");
		int respCode = resp.getStatusCode();
		System.out.println("Response Code for create user POST Call is: "+respCode);
		Assert.assertEquals(201, respCode);
		String responseBody = resp.getBody().asString();
		System.out.println("Response of successful user creation is : "+responseBody);
		
		JsonPath jsonPathEvaluator = resp.jsonPath();
		String createdAt = jsonPathEvaluator.get("createdAt");
		String id = jsonPathEvaluator.get("id");
		System.out.println("New user has been created At: "+createdAt+ " and id is: "+id);
		
		Assert.assertTrue("Create User is not Successful", responseBody.contains("createdAt"));
	}
	
	@Test(priority=3)
	public void Update_User()
	{
		RestAssured.baseURI = "https://reqres.in";
		RequestSpecification request = RestAssured.given(); 
		request.header("Content-Type", "application/json");
		
		//update of single user by single JSONObject
		JSONObject json = new JSONObject();
		json.put("name", "Jack");
		json.put("job", "Mechanical");
		
		request.body(json.toJSONString());
		Response resp = request.put("/api/users/636");
		int respCode = resp.getStatusCode();
		System.out.println("Response Code for update user PUT Call is: "+respCode);
		Assert.assertEquals(200, respCode);
		String responseBody = resp.getBody().asString();
		System.out.println("Response of successful user update is : "+responseBody);
		
		JsonPath jsonPathEvaluator = resp.jsonPath();
		String updatedAt = jsonPathEvaluator.get("updatedAt");
		System.out.println("User has been updated At: "+updatedAt);
		
		Assert.assertTrue("Update User is not Successful", responseBody.contains("updatedAt"));	
	}
	
	@Test(priority=4)
	public void Login_Success()
	{
		RestAssured.baseURI = "https://reqres.in";
		RequestSpecification request = RestAssured.given(); 
		request.header("Content-Type", "application/json");
		
		//Login by single user by single JSONObject
		JSONObject json = new JSONObject();
		json.put("email", "User3@icici");
		json.put("password", "Test@654");
		
		request.body(json.toJSONString());
		Response resp = request.post("/api/login");
		int respCode = resp.getStatusCode();
		System.out.println("Response Code for Login Success POST Call is: "+respCode);
		Assert.assertEquals(200, respCode);
		String responseBody = resp.getBody().asString();
		System.out.println("Response of successful Login : "+responseBody);
		
		JsonPath jsonPathEvaluator = resp.jsonPath();
		String token = jsonPathEvaluator.get("token");
		System.out.println("Token from successful login call: "+token);
		
		Assert.assertTrue("Login is not Successful", token.equals("QpwL5tke4Pnpja7X"));
		//Assert.assertEquals("Login Successful", token, "QpwL5tke4Pnpja7X");
	}
	
}
