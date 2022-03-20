package review_with_oscar;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
import static org.hamcrest.Matchers.*;

public class DesiarializationExamples {

   String zipUrl="https://api.zippopotam.us";
    String hrUrl="http://3.87.88.214:1000/ords/hr";

    @Test
    public void collectionTest(){
        Response response=given().accept(ContentType.JSON)
                .and().pathParam("zip",22031)
                .when().get(zipUrl+"/us/{zip}");

        response.prettyPrint();

        Map<String,Object> map=response.as(Map.class);

        List<Map<String,Object>> places= (List<Map<String, Object>>) map.get("places");

        System.out.println("map = " + map);
        System.out.println("places = " + places);

        assertEquals(map.get("country"),"United States");
        assertEquals(places.get(0).get("place name"),"Fairfax");

        double expectedLatitude = 38.8604;
        double actualLatitude=Double.parseDouble((String)places.get(0).get("latitude"));

        assertEquals(expectedLatitude,actualLatitude);
    }

    /*{"job_id": "AD_VP"}
Test Case:
{{hrurl}}/employees?q={"job_id": "AD_VP"}
Verify:
"employee_id": 102,
"first_name": "Lex",
 last_name": "De Haan",
 "href": "http://54.91.210.3:1000/ords/hr/employees/102"
    "count": 2,*/

    @Test
    public void hrCollectionTest(){
        Response response=given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().queryParam("q","{\"job_id\":\"AD_VP\"}")
                .when().get(hrUrl+"/employees");

        response.prettyPrint();

        Map<String,Object> resultMap=response.body().as(Map.class);
        System.out.println("resultMap = " + resultMap);


        List<Map<String,Object>>items= (List<Map<String, Object>>) resultMap.get("items");
        int employee_id = (int) items.get(1).get("employee_id");

        assertEquals(employee_id,102);
        assertEquals(items.get(1).get("first_name"),"Lex");
        assertEquals(items.get(1).get("last_name"),"De Haan");

        List<Map<String,Object>> links= (List<Map<String, Object>>) items.get(1).get("links");
        String href = (String) links.get(0).get("href");

        assertEquals(href,"http://3.87.88.214:1000/ords/hr/employees/102");
        int count = (int) resultMap.get("count");
        assertEquals(count,2);
    }

}
