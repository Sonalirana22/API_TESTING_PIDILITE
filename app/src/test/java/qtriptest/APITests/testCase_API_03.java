package qtriptest.APITests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
// import org.json.JSONObject;
import org.openqa.selenium.json.Json;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.UUID;

public class testCase_API_03 {

    @Test(groups = "API Tests")
    public void TestCase03() {

        RestAssured.baseURI = "https://content-qtripdynamic-qa-backend.azurewebsites.net";
        RestAssured.basePath = "/api/v1/register";

        // Random random = new Random();
        // randomEmail = "test"+random.nextInt()+"@gmail.com";
        Random random = new Random();
        String randomEmail = "test" + random.nextInt() + "@gmail.com";

        HashMap<String, String> map = new HashMap<>();
        map.put("email", randomEmail);
        map.put("password", "test@1235");
        map.put("confirmpassword", "test@1235");

        JSONObject json = new JSONObject(map);



        // String payload
        // ="{\"email\":\"anything12@gmail.com\",\"password\":\"xyz213@gmail.com\",\"confirmpassword\":\"xyz213@gmail.com\"}";

        RequestSpecification httpRequest =
                RestAssured.given().header("Content-Type", "application/json");
        httpRequest.body(json);

        Response response = httpRequest.request(Method.POST);

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 201);



        RestAssured.baseURI = "https://content-qtripdynamic-qa-backend.azurewebsites.net";
        RestAssured.basePath = "/api/v1/login";

        // Random random = new Random();
        // String randomEmail = "test"+random.nextInt()+"@gmail.com";

        HashMap<String, String> map2 = new HashMap<>();
        map2.put("email", randomEmail);
        map2.put("password", "test@1235");
        // map.put("confirmpassword","test@1235");

        JSONObject json2 = new JSONObject(map2);

        // String payload ="{\"email\":\"anything12@gmail.com\",\"password\":\"xyz213@gmail.com\"}";

        RequestSpecification httpRequest2 =
                RestAssured.given().header("Content-Type", "application/json");
        httpRequest2.body(json2);

        Response response2 = httpRequest2.request(Method.POST);

        int statusCode2 = response2.getStatusCode();
        Assert.assertEquals(statusCode2, 201);

        JsonPath jsonPath = response2.jsonPath();

        String token = jsonPath.get("data.token").toString();
        //System.out.println("token is :"+token);

        String id = jsonPath.get("data.id").toString();
        //System.out.println("id :"+id);

        JSONObject jsonObject = new JSONObject();

        String name = "test1"+random.nextInt();

        jsonObject.put("userId", id);
        jsonObject.put("name", name);
        jsonObject.put("date", "2023-12-12");
        jsonObject.put("person", "2");
        jsonObject.put("adventure", "2447910730");

        RestAssured.basePath = "api/v1/reservations/new";

        RequestSpecification httpRequest3 = RestAssured.given().log().all();
        httpRequest3.header("Content-Type", "application/json");

        httpRequest3.header("Authorization"," Bearer " + token).queryParam("q", "beng");

        httpRequest3.body(jsonObject.toString());

        Response response3 = httpRequest3.request(Method.POST);

        int statusCode3 = response3.getStatusCode();

        Assert.assertEquals(statusCode3, 200);

        //System.out.println("status code :"+statusCode3);


        RestAssured.basePath = "/api/v1/reservations";

        RequestSpecification httpRequest4 = RestAssured.given().log().all();
        //httpRequest4.header("Content-Type", "application/json");
        httpRequest4.queryParam("id", id);

        httpRequest4.header("Authorization"," Bearer " + token);

        Response response4 = httpRequest4.request(Method.GET);

        int statusCode4 = response4.getStatusCode();
        Assert.assertEquals(statusCode4, 200);

        // System.out.println(response4.prettyPeek());
        // System.out.println("status code :"+statusCode4);




    }

}
