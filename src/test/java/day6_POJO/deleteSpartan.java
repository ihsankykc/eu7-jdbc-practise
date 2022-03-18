package day6_POJO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class deleteSpartan {

    @BeforeClass
    public void beforeClass(){
        baseURI= ConfigurationReader.get("spartan_api_url");
    }

    @Test
    public void deleteSpartan(){

        List<Integer>ids=new ArrayList<>();
        for (int i = 170; i < 280; i++) {
             ids.add(i);
        }

        for (int id : ids) {
            Response response=given().accept(ContentType.JSON)
                    .and().contentType(ContentType.JSON)
                    .and().pathParam("id",id)
                    .when().delete("api/spartans/{id}");

            assertEquals(response.statusCode(),204);
        }
    }
}
