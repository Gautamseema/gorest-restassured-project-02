package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UserAssertionTest extends TestBase  {
    static ValidatableResponse response;

    @BeforeClass
    public static void response() {
//        RestAssured.baseURI = "https://gorest.co.in";
//        RestAssured.basePath =  "/public/v2";
        response = given()
                .when()
                .get("/users?page=1&per_page=20")
                .then().statusCode(200);
        response.log().all();
    }
//1. Verify the if the total record is 20
@Test
public void Test01() {
    response.body("size()", equalTo(20));

}
//2. Verify the if the name of id = 5914155 is equal to ”Bhishma Joshi”
@Test
public void Test02() {
    response.body("[0].name", equalTo("Bhishma Joshi"));
}
//3. Check the single ‘Name’ in the Array list (Pres. Shresth Kakkar)
@Test
public void Test03() {
    response.body("[2].name", equalTo("Pres. Shresth Kakkar"));
}
//4. Check the multiple ‘Names’ in the ArrayList (Ms. Agnimitra Varrier.,Dinesh Mehrotra, Somu Pillai )
@Test
public void Test04() {
    response
            .body("[10].name", equalTo("Ms. Agnimitra Varrier"))
            .body("[6].name", equalTo("Dinesh Mehrotra"))
            .body("[4].name", equalTo("Somu Pillai"));
}

//5. Verify the email of userid = 5914148 is equal “anshula_joshi@dare.test”
@Test
public void Test05() {
    response.body("[7].email", equalTo("sr_ahluwalia_tanirika@rau-williamson.test"));
}
//6. Verify the status is “Active” of user name is “Somu Pillai”
@Test
public void verifyTheStatus() {
    response.body("[8].status", equalTo("active"));
}

//7. Verify the Gender = male of user name is “Dinesh Mehrotra”
@Test
public void verifyTheGender() {
    //response.body("[10].findAll{it.name == 'Dinesh Mehrotra'}", hasItem(hasEntry("gender", "male")));
    response.body("[9].gender", equalTo("male"));

}
//
}
