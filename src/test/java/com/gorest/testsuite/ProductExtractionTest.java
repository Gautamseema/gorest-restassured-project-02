package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ProductExtractionTest extends TestBase  {
    static ValidatableResponse response;

    @BeforeClass
    public static void response() {
//        RestAssured.baseURI = "https://gorest.co.in";
//        RestAssured.basePath = "/public/v2";
        response = given()
                .when()
                .get("/posts?page=1&per_page=25")
                .then().statusCode(200);
        response.log().all();

    }

    //1. Extract the title
    @Test
    public void extractTheTitle() {
        String nameTitle = response.extract().path("findAll{it.id = 93953}.title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Title Of ID = 93953: " + nameTitle);
        System.out.println("------------------End of Test---------------------------");
    }

    //2. Extract the total number of record
    @Test
    public void extractTheRecord() {
        int records = response.extract().path("size()");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Total Number Of Records: " + records);
        System.out.println("------------------End of Test---------------------------");
    }

    //3. Extract the body of 15th record
    @Test
    public void extract15thRecord() {
        String record = response.extract().path("findAll{it.id}.body[5]");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The body Of 15th Records: " + record);
        System.out.println("------------------End of Test---------------------------");

    }

    //4. Extract the user_id of all the records
    @Test
    public void extractUserId() {
        List<?> userid = response.extract().path("user_id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The User IDs Of All Records: " + userid);
        System.out.println("------------------End of Test---------------------------");

    }

    //5. Extract the title of all the records
    @Test
    public void extractTitle() {
        List<?> title = response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The User IDs Of All Records: " + title);
        System.out.println("------------------End of Test---------------------------");

    }

    //6. Extract the title of all records whose user_id = 5914254
    @Test
    public void test006(){
        List<?> title = response.extract().path("title");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The title of all the records whose user_id is 5914254 : " + title);
        System.out.println("------------------End of Test---------------------------");
    }

    //7. Extract the body of all records whose id = 94000
    @Test
    public void test007(){
        List<?> body = response.extract().path("body");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The body of all records whose id is 94000 : " + body);
        System.out.println("------------------End of Test---------------------------");
}
}
