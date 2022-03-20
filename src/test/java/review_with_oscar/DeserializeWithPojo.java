package review_with_oscar;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeserializeWithPojo {

    String zipUrl="https://api.zippopotam.us";
    @Test
    public void pojoTest() {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("zip", 22031)
                .when().get(zipUrl + "/us/{zip}");

        response.prettyPrint();

        // ZipCode zipCode22031=response.as(ZipCode.class);
        Gson gson=new Gson();
        ZipCode zipCode22031=gson.fromJson(response.body().asString(),ZipCode.class);

        System.out.println("zipCode22031.getCountry() = " + zipCode22031.getCountry());

        System.out.println("getPlaceName() = " + zipCode22031.getPlaces().get(0).getPlaceName());

    }

}
