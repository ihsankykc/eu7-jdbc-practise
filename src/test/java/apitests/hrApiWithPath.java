package apitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

public class hrApiWithPath {
    @BeforeClass
    public void beforeClass(){
        RestAssured.baseURI= ConfigurationReader.get("hr_api_url");
    }


    @Test
    public void getCountriesWithPath(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\":2}")
                .get("/countries");

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json");

        //print limit value
        System.out.println("response limit = " + response.path("limit"));
        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));

        String firstCountryId = response.path("items.country_id[0]");
        System.out.println("firstCountryId = " + firstCountryId);

        String secondCountryName= response.path("items.country_name[1]");
        System.out.println("secondCountryName = " + secondCountryName);

       String hrefCanada=response.path("items.links[2].href[0]");
        System.out.println("hrefCanada = " + hrefCanada);

        //get all countries
        List<String> countryNames = response.path("items.country_name");
        System.out.println("countryNames = " + countryNames);

        //assert that all region_ids=2
        List<Integer >  regionIds= response.path("items.region_id");

        for (int regionId : regionIds) {
            Assert.assertEquals(regionId,2);
        }
   }

    @Test
    public void test2(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\":\"IT_PROG\"}")
                .when().get("/employees");

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json");

        List<String> jobIDs=response.path("items.job_id");

        for (String jobID : jobIDs) {
            Assert.assertEquals(jobID,"IT_PROG");
            System.out.println("jobID = " + jobID);
        }

//        Assert.assertEquals(response.statusCode(),200);
//        Assert.assertEquals(response.contentType(),"application/json");
//        Assert.assertTrue(response.body().asString().contains("IT_PROG"));

        //make sure we have only IT_PROG as a job_id
    }

}
