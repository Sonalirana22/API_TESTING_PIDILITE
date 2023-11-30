package qtriptest.APITests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
//import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Random;
import java.util.UUID;

public class testCase_API_04 {
    
    @Test(groups = "API Tests")
    public void TestCase04(){
        RestAssured.baseURI ="https://content-qtripdynamic-qa-backend.azurewebsites.net";
        RestAssured.basePath="/api/v1/register";

        Random random = new Random();
        String email = "test" +random.nextInt()+"@gmail.com";
        //System.out.println(email);

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("email", email);
        jsonObject.put("password", "abc321");
        jsonObject.put("confirmpassword", "abc321");

        RequestSpecification httpRequest = RestAssured.given().header("Content-Type", "application/json");
        httpRequest.body(jsonObject.toString());
        Response response = httpRequest.request(Method.POST);
        //System.out.println(response.prettyPeek());
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 201);

        JsonPath jsonPath = response.jsonPath();

        String success1 = jsonPath.get("success").toString();
        //System.out.println(success1);

        Response response2 = httpRequest.request(Method.POST);
        //System.out.println(response2.prettyPeek());
        int statusCode2 = response2.getStatusCode();
        Assert.assertEquals(statusCode2, 400);

        JsonPath jsonPath2 = response2.jsonPath();

        String success2 = jsonPath2.get("success").toString();
        //System.out.println(success2);

        String message2 = jsonPath2.get("message").toString();
        //System.out.println(message2);
        
        
    }
    
}

  

