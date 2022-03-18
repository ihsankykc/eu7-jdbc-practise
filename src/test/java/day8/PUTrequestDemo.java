package day8;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;


public class PUTrequestDemo {

    @BeforeClass
    public void beforeClass(){
        baseURI= ConfigurationReader.get("spartan_api_url");
    }

    @Test
    public void test1(){
        //Create one map for the put request json body
        Map<String,Object> putRequestMap=new HashMap<>();
        putRequestMap.put("gender","Male");
        putRequestMap.put("name","George");
        putRequestMap.put("phone",12345678910l);

        Response response=given().log().all()
                .and().contentType(ContentType.JSON)
                .and().body(putRequestMap)
                .and().pathParam("id",301)
                .when().put("api/spartans/{id}");

        assertEquals(response.statusCode(),204);


        response.prettyPrint();
    }

    @Test
    public void PatchTest(){
        //Create one map for the put request json body
        Map<String,Object> patchRequestMap=new HashMap<>();
//        patchRequestMap.put("gender","Male");
//        patchRequestMap.put("name","George");
        patchRequestMap.put("phone",54345678910l);

        Response response=given().log().all()
                .and().contentType(ContentType.JSON)
                .and().body(patchRequestMap)
                .and().pathParam("id",301)
                .when().patch("api/spartans/{id}");

        assertEquals(response.statusCode(),204);


        response.body().prettyPrint();
    }

    @Test
    public void deleteSpartan(){

        Response response=given().log().all()
                .and().pathParam("id",303)
                .when().delete("api/spartans/{id}");

        assertEquals(response.statusCode(),204);



        response.prettyPrint();
    }


}
