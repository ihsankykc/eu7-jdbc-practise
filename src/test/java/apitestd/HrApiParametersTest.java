package apitestd;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

public class HrApiParametersTest {
    @BeforeClass
    public void beforeClass(){
        RestAssured.baseURI= ConfigurationReader.get("hr_api_url");
    }

    @Test
    public void test1(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().queryParam("q", "{region_id:2}")
                .when().get("/countries");

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json");

        Assert.assertTrue(response.body().asString().contains("United States Of America"));



    }
}
