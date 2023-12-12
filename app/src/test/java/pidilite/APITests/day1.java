package pidilite.APITests;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class day1 {
    @Test
    public void TestCase(){

        RestAssured.baseURI ="https://jsonplaceholder.typicode.com";
        RestAssured.basePath="/posts";

        JSONObject jsonObject = new JSONObject();

        // Retrieve data from an endpoint

        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.body(jsonObject);

        Response response = httpRequest.request(Method.GET);
        System.out.println(response.prettyPeek());

        //Create a record

        RestAssured.basePath="/posts";

        JSONObject jsonObject2 = new JSONObject();
        
        jsonObject2.put("userId", "11");
        jsonObject2.put("id", "102");
        jsonObject2.put("title", "Sample Title");
        jsonObject2.put("body","cupiditate quo est a modi nesciunt soluta\nipsa voluptas error itaque dicta in\nautem qui minus magnam et distinctio eum\naccusamus ratione error aut");


        RequestSpecification httpRequest2 = RestAssured.given().header("Content-Type", "application/json");
        httpRequest2.body(jsonObject2);

        Response response2 = httpRequest2.request(Method.POST);

        int statusCode = response2.getStatusCode();
        Assert.assertEquals(statusCode, 201);
        System.out.println(statusCode);

        //Update the existing record

        RestAssured.basePath="posts/1";

        jsonObject2.put("userId", "1");
        jsonObject2.put("id", "10");
        jsonObject2.put("title", "API Testing");
        jsonObject2.put("body","cupiditate quo est a modi nesciunt soluta\nipsa voluptas error itaque dicta in\nautem qui minus magnam et distinctio eum\naccusamus ratione error aut");


        RequestSpecification httpRequest3 = RestAssured.given().header("Content-Type", "application/json");
        httpRequest3.body(jsonObject2);

        Response response3 = httpRequest3.request(Method.PUT);

        int statusCode2 = response3.getStatusCode();
        Assert.assertEquals(statusCode2, 200);

        System.out.println(statusCode2);

        //Delete Record

        RestAssured.basePath="posts/1";

        RequestSpecification httpRequest4 = RestAssured.given().header("Content-Type", "application/json");
        // httpRequest4.body(jsonObject2);

        Response response4 = httpRequest4.request(Method.DELETE);

        int statusCode3 = response4.getStatusCode();
        Assert.assertEquals(statusCode3, 200);

        System.out.println(statusCode3);

    }   
    
}
