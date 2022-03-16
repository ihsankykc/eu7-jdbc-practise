package apitestd;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class HamcrestMatchersApiTest {

    /*
    * Given accept type is Json
    * And pathParam id is 15
    * When user sends a get request to spartans/{id}
    * Then status code is 200
    * And content type is json
    * And json data has following
    *   "id"=15,
    *   "name":"Meta"
    *   "gender":"Female"
    *   "phone":1938695106
    * */
    @Test
    public void OneSpartanHamcrest(){
        given().accept(ContentType.JSON)
                .and().pathParam("id",15).
                when().get("http://3.87.88.214:8000/api/spartans/{id}")
                .then().statusCode(200)
                .and().contentType("application/json")
                .and().assertThat().body("id",equalTo(15))
                .and().assertThat().body("name",equalTo("Meta"))
                .and().assertThat().body("gender",equalTo("Female"))
                .and().assertThat().body("phone",equalTo(1938695106));
    }

    //There is no teacher Id in the database
    @Test
    public void teacherData(){
        given().accept(ContentType.JSON)
                .and().pathParam("id",8261)
                .when().log().all().get("http://api.cybertektraining.com/teacher/{id}")
                .then().assertThat().statusCode(200)
                .and().contentType(equalTo("application/json;charset=UTF-8"))
                .and().header("Content-Length",equalTo("240"))
                .and().header("Connection",equalTo("Keep-Alive"))
                .and().header("Date",notNullValue())
                .and().assertThat().body("teachers.firstName[0]",equalTo("James"),
                        "teachers.lastName[0]",equalTo("Bond"),
                        "teachers.gender[0]",equalTo("Male"))
                .log().all();
    }
}
