package pidilite.APITests;

import org.testng.annotations.DataProvider;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class day2 {

      //Edge Cases or Negative scenarios

      @Test(dataProvider = "dataProvider")
      public void edgeCaseScenarios(int id, int userId){

          RestAssured.baseURI ="https://jsonplaceholder.typicode.com";


          // Edge case scenario special charcters in input

          RestAssured.basePath="/posts"; 

          JSONObject jsonObject = new JSONObject();
      
          jsonObject.put("userId", userId);
          jsonObject.put("id", id);
          jsonObject.put("title", "&ljdsf2947634258$90@#");
          jsonObject.put("body","cupiditate quo est a modi nesciunt soluta\nipsa voluptas error itaque dicta in\nautem qui minus magnam et distinctio eum\naccusamus ratione error aut ansjhf jwbsdhwtfka kjsbdxgsyufcduixhso cupiditate quo est a modi nesciunt soluta\nipsa voluptas error itaque dicta in\nautem qui minus magnam et distinctio eum\naccusamus ratione error aut");


          RequestSpecification httpRequest2 = RestAssured.given().header("Content-Type", "application/json");
          httpRequest2.body(jsonObject);

          Response response2 = httpRequest2.request(Method.POST);
          Assert.assertEquals(response2.getStatusCode(), 201); 

          //Edge Case - invalid parameter value

          RestAssured.basePath="/posts";

          RequestSpecification httpRequest3 = RestAssured.given().param("userId", userId);
          httpRequest3.param("id", id);

          Response response3 = httpRequest3.request(Method.GET);
           
          System.out.println(response3.body().prettyPeek()); //Here we are getting empty body  with 200 status code

          Assert.assertEquals(response3.getStatusCode(), 200);

             //Negative Scenario-Invalid endpoint

             RestAssured.basePath="/posts/999999";  //attempt to retrieve a record that doesn't exists

             RequestSpecification httpRequest = RestAssured.given();
 
             Response response = httpRequest.request(Method.GET);
             Assert.assertEquals(response.getStatusCode(), 404);


        // Negative Scenario - Invalid input update

          RestAssured.basePath="/posts";

          RequestSpecification httpRequest4 = RestAssured.given().param("userId", userId);

          httpRequest4.param("id", id);

          Response response4 = httpRequest4.request(Method.PUT);
           
          System.out.println(response4.body().prettyPeek()); 

          Assert.assertEquals(response4.getStatusCode(), 404);



      }

      //Validate Response Headers

      @Test
      public void headerResponse(){

          RestAssured.baseURI ="https://jsonplaceholder.typicode.com";
          RestAssured.basePath="/users";

          RequestSpecification httpRequest = RestAssured.given();
          Response response = httpRequest.request(Method.GET);
          String contentType= response.header("Content-Type");
          System.out.println(contentType);
          Assert.assertEquals(contentType, "application/json; charset=utf-8");
          String cacheControl= response.header("Cache-Control");
          System.out.println(cacheControl);
          Assert.assertEquals(cacheControl, "max-age=43200");


      }

      @DataProvider(name = "dataProvider")
       public Object[][] methodDPObjects() {
      return new Object[][] 
      {{ 1,100 }, 
      { 10,200 }, 
      { -1,100 },  
      { 0,200 }};
  }
    
}
