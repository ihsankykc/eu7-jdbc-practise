package apitests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class CBTrainingWithJsonPath {
    @BeforeClass
    public void beforeClass(){
        baseURI= ConfigurationReader.get("cbt_api_url");
    }

    @Test
    public void test1(){
       Response response =given().accept(ContentType.JSON).
                and().pathParam("id",24668).log().all().get("/student/{id}");


       //verify status code
       assertEquals(response.statusCode(),200);

       //assign response to jsonpath
        JsonPath jsonPath=response.jsonPath();

        String firstname=jsonPath.getString("students.firstName[0]");
        System.out.println("firstname = " + firstname);

        String lastName=jsonPath.getString("students.lastName[0]");
        System.out.println("lastName = " + lastName );

        String phone=jsonPath.getString("students.contact[0].phone");
        System.out.println("phone = " + phone);

        String city = jsonPath.getString("students.company[0].address.city");
        System.out.println("city = " + city);

        String firstname2=jsonPath.getString("students.firstname");
        System.out.println("firstname = " + firstname);
    }


}
