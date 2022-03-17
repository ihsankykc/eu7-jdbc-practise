package day6_POJO;

import static io.restassured.RestAssured.*;
import static   org.testng.Assert.*;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class Pojo_deserialize {

    @Test
    public void oneSpartanPojo(){
        Response response=given().accept(ContentType.JSON)
                .and().pathParam("id",15)
                .when().get("http://3.87.88.214:8000/api/spartans/{id}");
        assertEquals(response.statusCode(),200);

        //json to POJO-->de-serialize to java custom class
        //Json to our spartan class object
        Spartan spartan15=response.body().as(Spartan.class);

        System.out.println(spartan15);
        System.out.println(spartan15.getName());
        System.out.println(spartan15.getId());

        assertEquals(spartan15.getId(),15);
        assertEquals(spartan15.getName(),"Meta");
    }

    @Test
    public void regionToPojo(){
        Response response= given().accept(ContentType.JSON)
                .when().get("http://3.87.88.214:1000/ords/hr/regions");

        assertEquals(response.statusCode(),200);

        //Json to POJO
        Regions regions=response.body().as(Regions.class);

        System.out.println("regions.getHasMore() = " + regions.getHasMore());
        System.out.println("regions.getCount() = " + regions.getCount());

        //get europe
        System.out.println(regions.getItems().get(0).getRegionName());

        List<Item> items = regions.getItems();
        System.out.println(items.get(1).getRegionId());

    }

    @Test
    public void gson_example(){
        Gson gson=new Gson();

        //Json to java collections orPoJo-->deserialization
        String myJsonData= "{\n" +
                "    \"id\": 15,\n" +
                "    \"name\": \"Meta\",\n" +
                "    \"gender\": \"Female\",\n" +
                "    \"phone\": 1938695106\n" +
                "}";

        //cast into a map collection
        Map<String,Object> map = gson.fromJson(myJsonData, Map.class);
        System.out.println("map = " + map);

        //cast into custom class
        Spartan spartan15=gson.fromJson(myJsonData,Spartan.class);
        System.out.println(spartan15);

        //------------------SERIALIZATION-----------
        //JAVA Collection or POJO to JSON
         Spartan spartanEU=new Spartan(200,"ihsan","Male",123545674);

        String jsonSpartanEU = gson.toJson(spartanEU);
        System.out.println("jsonSpartanEU = " + jsonSpartanEU);


    }
}
