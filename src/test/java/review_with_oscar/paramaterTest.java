package review_with_oscar;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
import static org.hamcrest.Matchers.*;

public class paramaterTest {

    @BeforeClass
    public void beforeClass(){
        baseURI="https://api.zippopotam.us";
    }

    /*
Given Accept application/json
And path zipcode is 22031
When I send a GET request to /us endpoint
Then status code must be 200
And content type must be application/json
And Server header is cloudflare
And Report-To header exists
And body should contains following information
    post code is 22031
    country  is United States
    country abbreviation is US
    place name is Fairfax
    state is Virginia
    latitude is 38.8604
     */
    @Test
    public void pathTest(){
        Response response=given().accept(ContentType.JSON)
                .and().pathParam("zip",22031)
                .when().get("/us/{zip}");

      //response.prettyPrint();

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");

//       And Server header is cloudflare
//       And Report-To header exists
        assertTrue(response.headers().hasHeaderWithName("Report-To"));
        assertEquals(response.header("Server"),"cloudflare");

//    And body should contains following information
//      post code is 22031
        assertEquals(response.path("\'post code\'"),"22031");
//      country  is United States
        assertEquals(response.path("country"),"United States");
//      country abbreviation is US
        assertEquals(response.path("\'country abbreviation\'"),"US");
//      place name is Fairfax
        assertEquals(response.path("places.\'place name\'[0]"),"Fairfax");
//      state is Virginia
        assertEquals(response.path("places.state[0]"),"Virginia");
//      latitude is 38.8604
        assertEquals(response.path("places.latitude[0]"),"38.8604");

        JsonPath jsonPath=response.jsonPath();
        jsonPath.prettyPrint();

        //JSONPATH METHOD
//      state is Virginia
        assertEquals(jsonPath.getString("places.state[0]"),"Virginia");
//      latitude is 38.8604
        assertEquals(jsonPath.getString("places.latitude[0]"),"38.8604");

    }

    @Test
    public void hemCrestWithZipApi(){
        given().accept(ContentType.JSON)
                .and().pathParam("zip","22031")
                .when().log().all().get("/us/{zip}")
                .then().statusCode(200)
                .and().contentType("application/json")
                .log().body()
                .and().header("Server",equalTo("cloudflare"))
                .and().header("Report-To",notNullValue())
                .and().body("country",equalTo("United States"))
                .body("\'country abbreviation\'",equalTo("US"))
                .body("places.\'place name\'[0]",equalTo("Fairfax"))
                .body("places.state[0]",equalTo("Virginia"),
                        "places.latitude[0]",equalTo("38.8604"));
    }

    /*
    * Given Accept application/json
    And path zipcode is 50000
    When I send a GET request to /us endpoint
    Then status code must be 404
    And content type must be application/json*/

    @Test
    public void negativeWithHemcrest(){
        given().accept(ContentType.JSON)
                .and().pathParam("zip","50000")
                .when().log().all().get("/us/{zip}")
                .then().statusCode(404)
                .and().contentType("application/json")
                .and().log().body();
    }

    @Test
    public void negativeWithJsonPath(){
        Response response=given().accept(ContentType.JSON)
                .and().pathParam("zip",50000)
                .when().get("/us/{zip}");

        JsonPath jsonPath=response.jsonPath();

        assertEquals(response.statusCode(),404);
        assertEquals(response.contentType(),"application/json");

    }
}
