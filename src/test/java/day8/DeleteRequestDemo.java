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

public class DeleteRequestDemo {
    @BeforeClass
    public void beforeClass(){
        baseURI= ConfigurationReader.get("spartan_api_url");
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
