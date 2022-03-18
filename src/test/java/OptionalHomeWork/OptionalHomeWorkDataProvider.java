package OptionalHomeWork;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;
import utilities.ExcelUtil;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;


public class OptionalHomeWorkDataProvider {

    //Homework
    //Optional homeworks
    //Homework-1
    //1-Create csv file from mackaroo website, which includes name,gender,phone
    //2-Download excel file
    //3- using testng data provider and apache poi create data driven posting from spartan

    @DataProvider
    public Object[][] userData(){
        ExcelUtil newSpartanList=new ExcelUtil("src/test/resources/MOCK_DATA.xlsx","data");

        return newSpartanList.getDataArrayWithoutFirstRow();
    }

    @Test(dataProvider = "userData")
    public void test(String name,String gender,String phone){

        Map<String,Object> requestMap=new HashMap<>();

        BigDecimal phone1=new BigDecimal(phone);


            requestMap.put("name",name);
            requestMap.put("gender",gender);
            requestMap.put("phone",phone1.longValue());



        Response response=given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(requestMap)
                .when().log().all().post(ConfigurationReader.get("spartan_api_url")+"api/spartans");

        response.prettyPrint();
        assertEquals(response.statusCode(),201);
        assertEquals(response.contentType(),"application/json");

        int id=response.body().path("data.id");

        Response response1=given().accept(ContentType.JSON)
                .and().pathParam("id",id)
                .when().get(ConfigurationReader.get("spartan_api_url")+"api/spartans/{id}");

        assertEquals(response1.statusCode(),200);
        assertEquals(response1.contentType(),"application/json");

        response1.prettyPrint();


    }


}
