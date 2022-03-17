package apitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class hrApiWithJsonPath {
    @BeforeClass
    public void beforeClass(){
        RestAssured.baseURI= ConfigurationReader.get("hr_api_url");
    }

    @Test
    public void test1(){
        Response response=given().accept(ContentType.JSON)
                .when().get("/countries/");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");

        //assign to jsonPath
        JsonPath jsonPath=response.jsonPath();

        String secondCountryName= jsonPath.getString("items.country_name[1]");
        System.out.println("secondCountryName = " + secondCountryName);

        List<String> countryList = jsonPath.getList("items.country_name");
        System.out.println("countryList = " + countryList);

        //get all country names where their region id is equal to 2
        List<String> countryNamesWithRegionId2= jsonPath.getList("items.findAll{it.region_id==2}.country_name");
        System.out.println("countryNamesWithRegionId2 = " + countryNamesWithRegionId2);


    }

    @Test
    public void test2(){
        Response response=given().accept(ContentType.JSON)
                .and().queryParam("limit",125)
                .when().get("/employees");

        JsonPath jsonPath=response.jsonPath();

        //get me all firstname of employees who is working as IT_PROG
        List<String> itProg = jsonPath.getList("items.findAll{it.job_id==\"IT_PROG\"}.first_name");
        System.out.println("itProg = " + itProg);

        //get me all firstname of employees who is making more than 10000
        List<String> highIncome = jsonPath.getList("items.findAll{it.salary>10000}.first_name");
        System.out.println("highIncome = " + highIncome);

        //get me firstname of who is making highest salary
        String kingName =jsonPath.getString("items.max{it.salary}.first_name");
        System.out.println("kingName = " + kingName);
    }
}
