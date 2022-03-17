package review_with_oscar.avengersAPIReview;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import  org.testng.Assert;
import  org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class SimpleGetRequests {

    String hrUrl="http://3.87.88.214:1000/ords/hr";

    //simple get request
    @Test
    public void test1(){
        Response response= given().accept(ContentType.JSON)
                .get(hrUrl+"/employees");

       // response.prettyPrint();

        //verify status code and content type
        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");

        //headers
        assertTrue(response.headers().hasHeaderWithName("Date"));
        assertEquals(response.header("Transfer-Encoding"),"chunked");
        System.out.println("response.headers() = " + response.headers());
    }


    @Test
    public void test2(){
        Response response=given().contentType(ContentType.JSON)
                .and().pathParam("id",105)
                .when().get(hrUrl+"/employees/{id}");

        assertEquals(response.statusCode(),200);

        //Gpath syntax
        String actualJobId= response.body().path("job_id");
        assertEquals(actualJobId,"IT_PROG");

        //create a jsonpath object from response object
        JsonPath jsonPath=response.jsonPath();
        assertEquals(jsonPath.getString("job_id"),"IT_PROG");


    }

    @Test
    public void test3(){
        Response response=given().accept(ContentType.JSON)
                .and().queryParam("q","{\"job_id\":\"AD_VP\"}")
                .when().get(hrUrl+"/employees");

        assertEquals(response.statusCode(),200);

        int count=response.path("count");
        assertEquals(count,2);
        response.prettyPrint();

        JsonPath jsonPath=response.jsonPath();

        int actualCount = jsonPath.getInt("count");

        assertEquals(actualCount,2);
    }

}
