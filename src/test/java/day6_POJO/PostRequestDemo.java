package day6_POJO;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;


public class PostRequestDemo {
    @BeforeClass
    public void beforeClass(){
        RestAssured.baseURI= ConfigurationReader.get("spartan_api_url");
    }

     /*
    Given accept type and Content type is JSON
    And request json body is:
    {
      "gender":"Male",
      "name":"MikeEU",
      "phone":8877445596
   }
    When user sends POST request to '/api/spartans'
    Then status code 201
    And content type should be application/json
    And json payload/response/body should contain:
    "A Spartan is Born!" message
    and same data what is posted
 */

    @Test
    public void PostNewSpartan(){

        String jsonBody=" {\n" +
                "      \"gender\":\"Male\",\n" +
                "      \"name\":\"MikeEU\",\n" +
                "      \"phone\":8877445596\n" +
                "   }";
        Response response=given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(jsonBody)
                .when().log().all().post("/api/spartans");

        assertEquals(response.statusCode(),201);
        assertEquals(response.contentType(),"application/json");

        //verify succesful message
        String actualMessage = response.path("success");
        String expectedMessage="A Spartan is Born!";

        assertEquals(actualMessage,expectedMessage);

        //assertion for spartan data
        String name=response.path("data.name");
        String gender=response.path("data.gender");
        long phone=response.path("data.phone");

        assertEquals(name,"MikeEU");
        assertEquals(gender,"Male");
        assertEquals(phone,8877445596l);
    }

    @Test
    public void PostNewSpartan2(){
        Map<String,Object> requestMap=new HashMap<>();

        Faker faker=new Faker();
        String name=faker.name().firstName().toString();
        System.out.println("name = " + name);


        requestMap.put("name",name);
        requestMap.put("gender","Male");
        requestMap.put("phone",8899445518l);

        given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(requestMap)
                .when().log().all().post("api/spartans")
                .then().log().all().statusCode(201)
                .and().contentType("application/json")
                .and().body("success", equalTo("A Spartan is Born!"))
                .and().body("data.name",equalTo(name))
                .and().body("data.gender",equalTo("Male"))
                .and().body("data.phone",equalTo(8899445518l));

    }

    @Test
    public void PostNewSpartan3 (){
        Faker faker=new Faker();
        String name = faker.name().firstName();

        Spartan spartanEU=new Spartan();
        spartanEU.setName(name);
        spartanEU.setGender("Male");
        spartanEU.setPhone(8899445518l);

        given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(spartanEU)
                .when().log().all().post("api/spartans")
                .then().log().all().statusCode(201)
                .and().contentType("application/json")
                .and().body("success", equalTo("A Spartan is Born!"))
                .and().body("data.name",equalTo(name))
                .and().body("data.gender",equalTo("Male"))
                .and().body("data.phone",equalTo(8899445518l));

    }

    @Test
    public void PostNewSpartan4(){
        //Homework
        //Optional homeworks
        //Homework-1
        //1-Create csv file from mackaroo website, which includes name,gender,phone
        //2-Download excel file
        //3- using testng data provider and apache poi create data driven posting from spartan

        //Homework-2
        //-Create one mackaroo api for name,gender,phone
        //send get request to retrieve random info from that api
        //use those info to send post request to spartan

        Faker faker=new Faker();
        String name = faker.name().firstName();

        Spartan spartanEU=new Spartan();
        spartanEU.setName(name);
        spartanEU.setGender("Male");
        spartanEU.setPhone(8899445518l);

        Response response=given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(spartanEU)
                .when().log().body().post("api/spartans");

        assertEquals(response.statusCode(),201);

        //after post request ,send a get request to generated spartan

        int id = response.path("data.id");
        System.out.println("id = " + id);

        Response response1=given().accept(ContentType.JSON)
                            .and().pathParam("id",id)
                            .when().get("/api/spartans/{id}");

        response1.prettyPrint();
        assertEquals(response1.statusCode(),200);
        assertEquals(response1.path("name"),name);


    }


}
