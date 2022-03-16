package apitestd;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class SpartanTestWithJsonPath {

    @BeforeClass
    public void beforeClass(){
        baseURI= ConfigurationReader.get("spartan_api_url");
    }

    /*
    * Given accept type is Json
    * And param Spartan Id is 11
    * When User sends a get request to /spartans/{id}
    * Content status type should be 200
    * And content type is json
    * And   id:11
    *       name="Nona"
    *       gender="Female
    *       "phone"=7959094216
    * */

    @Test
    public void test1(){

       Response response=given().accept(ContentType.JSON)
                .and().pathParam("id",11)
                .when().get("/api/spartans/{id}");

       assertEquals(response.statusCode(),200);
       assertEquals(response.contentType(),"application/json");

       //Verify id and name with path
        int id=response.path("id");
        String name=response.path("name");

        assertEquals(id,11);
        assertEquals(name,"Nona");

        //assign response to jsonPath
        JsonPath jsonPath=response.jsonPath();

        int idJson=jsonPath.getInt("id");
        String nameJson=jsonPath.getString("name");
        String genderJson=jsonPath.getString("gender");
        long phoneJson=jsonPath.getLong("phone");

        assertEquals(idJson,11);
        assertEquals(nameJson,"Nona");
        assertEquals(genderJson,"Female");
        assertEquals(phoneJson,7959094216l);

    }
}
