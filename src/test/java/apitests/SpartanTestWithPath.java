package apitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

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

    @Test
    public void getAllSpartanWithPath(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get("/api/spartans");

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json");

        //give path index
        System.out.println(response.path("id[0]").toString());

        int firstID=response.path("id[0]");
        System.out.println("firstID = " + firstID);

        String firstname=response.path("name[0]");
        System.out.println("firstname = " + firstname);

        String lastname=response.path("name[-1]");
        System.out.println("lastname = " + lastname);

        //print all names of spartans
        List<String> names = response.path("name");
        System.out.println(names);

        List<Object> phones=response.path("phone");
        for (Object phone : phones) {
            System.out.println(phone);
        }
    }
}
