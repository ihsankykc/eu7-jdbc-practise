package apitestd;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Optional;

public class SpartanTestWithPath {
    @BeforeClass
    public void beforeClass(){
        RestAssured.baseURI="http://3.87.88.214:8000/";
    }



    /*
    * Given accept type is json
    * And path param id is 10
    * When user sends a get request "api/spartans/{id}"
    * Then status code is 200
    * And content type is "application/json"
    * And response payload values match the following
    *       id is 10,
    *       name is "Lorenza"
    *       gender is female
    *       phone is 3312820936*/
    @Test
    public void test1(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id", "10")
                .when().get("api/spartans/{id}");

        Assert.assertEquals(response.statusCode(),200);

        Assert.assertEquals(response.contentType(),"application/json");

        response.prettyPrint();

        //save json key values
        int id=response.path("id");
        String name=response.path("name");
        String gender=response.path("gender");
        long phone=response.path("phone");

        Assert.assertEquals(id,10);
        Assert.assertEquals(name,"Lorenza");
        Assert.assertEquals(gender,"Female");
        Assert.assertEquals(phone,3312820936l);
    }
}
