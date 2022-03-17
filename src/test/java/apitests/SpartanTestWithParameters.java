package apitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class SpartanTestWithParameters {
    @BeforeClass
    public void beforeClass(){
        RestAssured.baseURI="http://3.87.88.214:8000";
    }

    /*
    * Given that accept type is Json
    * And ID parameter value is 5
    * When user sends Get request to /api/spartans/{id}
    * Then the response status code should be 200
    * and the response content-type:application/json;charset=UFT-8
    * and "Blythe" should be in response payload
    */
    @Test
    public void test1(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id",5)
                .when().get("/api/spartans/{id}");

        Assert.assertEquals(response.statusCode(),200);

        Assert.assertEquals(response.contentType(),"application/json");

        Assert.assertTrue(response.asString().contains("Blythe"));

    }

    /*
    * Given accept type is Json
    * And ID parameter value is 500
    * When user sends GET request to /api/spartans/{id}
    * Then response status code should be 404
    * And response content-type: application/json;charset=UTF-8
    * And "Spartans Not Found" message should be in response payload*/

    @Test
    public void test2(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id",500)
                .when().get("/api/spartans/{id}");

        Assert.assertEquals(response.statusCode(),404);

        Assert.assertEquals(response.contentType(),"application/json");

        Assert.assertTrue(response.body().asString().contains("Not Found"));
    }

    /*
    * Given accept type is Json
    * And query parameter values are :
    * gender|Female
    * nameContains|e
    * When user sends Get request to /api/spartans/search
    * And response content-type:application/json;charset=UTF-8
    * And "Female" should be in response payload
    * And "Janette" should be in response payload*/
    @Test
    public void positiveTestWithQueryParam(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().queryParam("gender", "Female")
                .and().queryParam("nameContains", "e")
                .when().get("/api/spartans/search");

        Assert.assertEquals(response.statusCode(),200);

        Assert.assertEquals(response.contentType(),"application/json");

        Assert.assertTrue(response.body().asString().contains("Female"));
        Assert.assertTrue(response.body().asString().contains("Janette"));

    }

    @Test
    public void positiveTestWithQueryParamsMaps(){
        //create a map and add query parameters
        Map<String,Object> queryMap=new HashMap<>();
        queryMap.put("gender","Female");
        queryMap.put("nameContains","e");

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().queryParams(queryMap)
                .when().get("/api/spartans/search");

        Assert.assertEquals(response.statusCode(),200);

        Assert.assertEquals(response.contentType(),"application/json");

        Assert.assertTrue(response.body().asString().contains("Female"));
        Assert.assertTrue(response.body().asString().contains("Janette"));

    }
}
