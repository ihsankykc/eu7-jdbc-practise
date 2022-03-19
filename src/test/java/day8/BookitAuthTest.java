package day8;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
import static org.hamcrest.Matchers.*;

public class BookitAuthTest {
    @BeforeClass
    public void beforeClass(){

        baseURI="https://cybertek-reservation-api-qa2.herokuapp.com";
    }

    @Test
    public void getAllCampuses(){

        String token="Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMzAiLCJhdWQiOiJzdHVkZW50LXRlYW0tbGVhZGVyIn0.3YSCwcTXRcEygBm7LvBLb6_D8jU5WXjAD6E3VA9oh0o";

        Response response = given().header("Authorization",token)
                .when().get("api/campuses");
        System.out.println("response.statusCode() = " + response.statusCode());
        response.prettyPrint();

    }
}
