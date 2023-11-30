package qtriptest.APITests;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;
import org.json.simple.JSONObject;
//
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;



public class testCase_API_01 {

    
    @Test(groups = "API Tests")
    public void TestCase01(){

        RestAssured.baseURI ="https://content-qtripdynamic-qa-backend.azurewebsites.net";
        RestAssured.basePath="/api/v1/register";

        // Random random = new Random();
        // randomEmail = "test"+random.nextInt()+"@gmail.com";
        Random random = new Random();
        String randomEmail = "test"+random.nextInt()+"@gmail.com";

        HashMap<String, String> map = new HashMap<>();
        map.put("email", randomEmail);
        map.put("password", "test@1235");
        map.put("confirmpassword","test@1235");

        JSONObject json = new JSONObject(map);

        

        //String payload ="{\"email\":\"anything12@gmail.com\",\"password\":\"xyz213@gmail.com\",\"confirmpassword\":\"xyz213@gmail.com\"}";
        
        RequestSpecification httpRequest = RestAssured.given().header("Content-Type", "application/json");
        httpRequest.body(json);
        
        Response response = httpRequest.request(Method.POST);

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 201);

    

        RestAssured.baseURI="https://content-qtripdynamic-qa-backend.azurewebsites.net";
        RestAssured.basePath="/api/v1/login";

            //Random random = new Random();
            //String randomEmail = "test"+random.nextInt()+"@gmail.com";

            HashMap<String, String> map2 = new HashMap<>();
            map2.put("email", randomEmail);
            map2.put("password", "test@1235");
            //map.put("confirmpassword","test@1235");

            JSONObject json2 = new JSONObject(map2);

        //String payload ="{\"email\":\"anything12@gmail.com\",\"password\":\"xyz213@gmail.com\"}";

        RequestSpecification httpRequest2 = RestAssured.given().header("Content-Type", "application/json");
        httpRequest2.body(json2);
        
        Response response2 = httpRequest2.request(Method.POST);

        int statusCode2 = response2.getStatusCode();
        Assert.assertEquals(statusCode2, 201);

        JsonPath jsonPath = response2.jsonPath();

        String token = jsonPath.get("data.token").toString();

        //System.out.println(token);


    }

   
}
