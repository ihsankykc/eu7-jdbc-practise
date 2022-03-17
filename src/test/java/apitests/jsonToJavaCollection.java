package apitests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
public class jsonToJavaCollection {
    @BeforeClass
    public void beforeClass(){
        baseURI= ConfigurationReader.get("spartan_api_url");
    }

    @Test
    public void SpartanMap(){

        Map<String,Object> jsonDataMap=new HashMap<>();

        Response response =given().accept(ContentType.JSON)
                .and().pathParam("id",15)
                .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(),200);

        //we will convert json response to java map
        jsonDataMap=response.body().as(Map.class);

        System.out.println(jsonDataMap.toString());

        String name= (String) jsonDataMap.get("name");
        assertEquals(name,"Meta");

        int phone=(int) jsonDataMap.get("phone");
        System.out.println("phone = " + phone);
    }

    @Test
    public void allSpartansToListOfMap(){

        Response response= given().accept(ContentType.JSON)
                .when().get("/api/spartans/");

        assertEquals(response.statusCode(),200);

        //we need to deserialize Json response to List of Maps
        List<Map<String,Object>>allSpartanList= response.body().as(List.class);

        System.out.println("allSpartanList = " + allSpartanList);

        //print second spartan first name
        System.out.println("second spartan first name = " + allSpartanList.get(1).get("name"));

        Map<String, Object> spartan3 = allSpartanList.get(2);
        System.out.println("spartan3 = " + spartan3);
    }

    @Test
    public void regionToMap(){
        Response response=given().accept(ContentType.JSON)
                .get("http://3.87.88.214:1000/ords/hr/regions");

        assertEquals(response.statusCode(),200);

        //we de-serialize Json response to Map
        Map<String,Object> regionMap=response.body().as(Map.class);

        System.out.println("count = " + regionMap.get("count"));
        System.out.println("limit = " + regionMap.get("limit"));

        //cast your items into List of maps
        List<Map<String,Object>> itemList = (List<Map<String, Object>>) regionMap.get("items");
        //get Europe
        System.out.println("region_name = " + itemList.get(0).get("region_name"));
    }
}
