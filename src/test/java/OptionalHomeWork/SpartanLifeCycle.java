package OptionalHomeWork;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.*;

public class SpartanLifeCycle {

    int id=101;

    @BeforeClass
    public void beforeClass(){
        baseURI= ConfigurationReader.get("spartan_api_url");
    }

    @Test
    public void PostNewSpartan(){
        Map<String,Object> requestMap=new HashMap<>();

        requestMap.put("name","Mike");
        requestMap.put("gender","Male");
        requestMap.put("phone",8899445518l);

        given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(requestMap)
                .when().log().all().post("api/spartans")
                .then().log().all().statusCode(201)
                .and().contentType("application/json")
                .and().body("success", equalTo("A Spartan is Born!"))
                .and().body("data.name",equalTo("Mike"))
                .and().body("data.gender",equalTo("Male"))
                .and().body("data.phone",equalTo(8899445518l));

    }

    @Test
    public void putExistingSpartan(){
        //Create one map for the put request json body
        Map<String,Object> putRequestMap=new HashMap<>();
        putRequestMap.put("gender","Male");
        putRequestMap.put("name","George");
        putRequestMap.put("phone",12345678910l);

        Response response=given().log().all()
                .and().contentType(ContentType.JSON)
                .and().body(putRequestMap)
                .and().pathParam("id",id)
                .when().put("api/spartans/{id}");

        assertEquals(response.statusCode(),204);


        //response.prettyPrint();
    }

    @Test
    public void PatchExistingSpartan(){
        //Create one map for the put request json body
        Map<String,Object> patchRequestMap=new HashMap<>();

        patchRequestMap.put("phone",54345678910l);

        Response response=given().log().all()
                .and().contentType(ContentType.JSON)
                .and().body(patchRequestMap)
                .and().pathParam("id",id)
                .when().patch("api/spartans/{id}");

        assertEquals(response.statusCode(),204);


       //response.body().prettyPrint();
    }

    @Test
    public void deleteExistingSpartan(){

        Response response=given().log().all()
                .and().pathParam("id",id)
                .when().delete("api/spartans/{id}");

        assertEquals(response.statusCode(),204);

        //response.prettyPrint();
    }
}
