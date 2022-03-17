package day6_POJO;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
import static org.hamcrest.Matchers.*;


public class HrPostRequest {
    @BeforeClass
    public void beforeClass(){
        RestAssured.baseURI= ConfigurationReader.get("hr_api_url");
    }

    @Test
    public void postRegion1(){
        RegionPost regionPost=new RegionPost(12,"Cybertek Nederlands");

        given().log().all()
                .and().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(regionPost)
                .when().post("/regions/")
                .then().statusCode(201)
                .and().contentType("application/json")
                .body("region_id",is(12));
    }
}
