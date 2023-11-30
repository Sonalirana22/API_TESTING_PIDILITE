package qtriptest.APITests;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;
//import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;
public class testCase_API_02 {

    @Test (groups = "API Tests")
    public void TestCase02(){

        RestAssured.baseURI = "https://content-qtripdynamic-qa-backend.azurewebsites.net";
        RestAssured.basePath = "/api/v1/cities";

        RequestSpecification httpRequest = RestAssured.given().param("q", "beng");
        
        Response response = httpRequest.request(Method.GET);
        //Response response2 = httpRequest.request().get();

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
       // System.out.println(response.asPrettyString());

        File schemaFile = new File("/home/crio-user/workspace/ranasonali143-ME_API_TESTING_PROJECT/app/src/test/resources/schema2.json");
	    JsonSchemaValidator jasonvalidator = JsonSchemaValidator.matchesJsonSchema(schemaFile);
	    response.then().assertThat().body(jasonvalidator);

        JsonPath path = response.jsonPath();
        String description = path.get("description").toString();
        //System.out.println(description);

        Assert.assertEquals(description, "[100+ Places]");

        List list = response.jsonPath().getList("");
       // Object[] integer =list.toArray();
        Assert.assertEquals(list.size(), 1);

    }





}
