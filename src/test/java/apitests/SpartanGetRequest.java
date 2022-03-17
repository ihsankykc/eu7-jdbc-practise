package apitests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SpartanGetRequest {

    String spartanUrl="http://3.87.88.214:8000";

    @Test
    public void test1(){
      //request part
       Response response = RestAssured.when().get(spartanUrl+"/api/spartans");

        System.out.println(response.statusCode());
        response.prettyPrint();
    }

    /*
    * When user sends a get request to /api/spartans/3
    * Then status code should be 200
    * and contetnt type should be application/json;charset=UFT-8
    * and json body should contain Fidole*/
    @Test
    public void test2(){
        Response response = RestAssured.get(spartanUrl + "/api/spartans/3");

        Assert.assertEquals(response.statusCode(),200);

        Assert.assertEquals(response.contentType(),"application/json;charset=UTF-8");

        response.prettyPrint();
        Assert.assertTrue(response.asString().contains("Fidole"));

    }

    /*
    * Given no headers provided
    * When user sends GET request to api/hello
    * the response status code should be 200
    * And the content type header should be "text/plain;charset=UFT-8"
    * And the header should contain date
    * And the content length should be 17
    * body: "Hello from Sparta" */

    @Test
    public void helloTest(){
        Response response = RestAssured.get(spartanUrl + "/api/hello");

        Assert.assertEquals(response.statusCode(),200);

        Assert.assertEquals(response.contentType(),"text/plain;charset=UTF-8");

        Assert.assertTrue(response.headers().hasHeaderWithName("Date"));

        Assert.assertEquals(response.body().asString().length(),17);

        Assert.assertEquals(response.body().asString(),"Hello from Sparta");
    }

}
