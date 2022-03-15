package apitestd;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class SimpleGetRequest {
    String hrurl= "http://3.87.88.214:1000/ords/hr/regions";

    @Test
    public void test1(){
        Response response = RestAssured.get(hrurl);
        //print the status code
        System.out.println(response.statusCode());

        //print jason body
        response.prettyPrint();
    }

    /*
    * Given accept type is json
    * When user sends get request to regions endpoint
    * Then the response status code must be 200
    * and body is json format
    * */
    @Test
    public void test2(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(hrurl);

        //verify status code is 200
        Assert.assertEquals(response.statusCode(),200);

        //verify content-type is application/json
        System.out.println(response.contentType());
        Assert.assertEquals(response.contentType(),"application/json");
    }

    @Test
    public void test3(){
        RestAssured.given().accept(ContentType.JSON)
                .when().get(hrurl)
                .then().assertThat().statusCode(200)
                .and().contentType("application/json");
    }

    /*
     * Given that accept type is json
     * When user sends get request to regions/2
     * Then the response status code must be 200
     * and body is json format
     * and response body contains Americas
     * */

    @Test
    public void test4(){
        Response response = given().accept(ContentType.JSON)
                .when().get(hrurl + "/2");

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json");

        //Verify body contains Americas
        Assert.assertTrue(response.body().asString().contains("Americas"));
    }


}
