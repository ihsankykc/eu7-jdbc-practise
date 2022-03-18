package OptionalHomeWork;

//Homework-2
//-Create one mackaroo api for name,gender,phone
//send get request to retrieve random info from that api
//use those info to send post request to spartan

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class OptionalHomeWorkMacKarooApi {

    @Test
    public void test(){

        //Send request to  mockaroo api to generate data
        Response response=given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().queryParam("key","3f9a59e0")
                .when().get(ConfigurationReader.get("mockApiUrl")+"/mock_api");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");

        //put your response data to List of Map
        List<Map<String,Object>> listMockApiSpartans=response.body().as(List.class);

        //create list to collect your ids
        List<Integer>ids=new ArrayList<>();

        //iterate your list of map
        //post your map data as new spartan

        for (Map<String, Object> mapMockApiSpartan : listMockApiSpartans) {

            Response response1=given().accept(ContentType.JSON)
                    .and().contentType(ContentType.JSON)
                    .and().body(mapMockApiSpartan)
                    .when().post(ConfigurationReader.get("spartan_api_url")+"api/spartans");

            //assert your post is successful
            assertEquals(response1.statusCode(),201);
            assertEquals(response1.contentType(),"application/json");

            //get your new spartan ids
            int id=response1.body().path("data.id");

            ids.add(id);

        }

        //iterate your ids list in order to verify new spartans
        //verify your spartan api is updated using id path param
        for (int id : ids) {
            Response response2=given().accept(ContentType.JSON)
                    .and().contentType(ContentType.JSON)
                    .and().pathParam("id",id)
                    .when().get(ConfigurationReader.get("spartan_api_url")+"api/spartans/{id}");
            assertEquals(response2.statusCode(),200);
            assertEquals(response2.contentType(),"application/json");

            //print your result
            //response2.prettyPrint();

        }

    }

}
