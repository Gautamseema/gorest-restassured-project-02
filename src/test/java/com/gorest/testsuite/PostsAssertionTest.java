package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostsAssertionTest extends TestBase {
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

    //1. Verify the if the total record is 25
    @Test
    public void test001() {
        response.body(("size()"), equalTo(25));

    }

    //2. Verify the if the title of id = 93997 is equal to ”Demitto conqueror atavus argumentum corrupti
//cohaero libero.”
    @Test
    public void test002() {
        response.body("findAll{it.id == 93997}.title", hasItem("Demitto conqueror atavus argumentum corrupti cohaero libero."));
    }

    //3. Check the single user_id in the Array list (5914249)
    @Test
    public void test003() {
        response.body("user_id", hasItem(5914249));
    }

    //4. Check the multiple ids in the ArrayList (5914251, 5914254,5914243)
    @Test
    public void test004() {

        response.body("user_id", hasItems(5914251, 5914254, 5914243));
    }

    //5. Verify the body of userid = 5914193 is equal “Dolorum caelum infit tristis super esse defleo.
//Currus alter tenax. Vado patruus coadunatio. Thymum caveo aut. Tepidus cetera saepe.
// Tredecim vulticulus aggero. Eaque enim aureus. Tabesco torqueo aut. Velit admoneo utique.
// Cur ager coniuratio. Animi ascisco tenuis. Autus sit bis. Suus colloco concedo.
// Vulgaris degero ciminatio. Velit vere tracto. Tamisium tracto antepono. Clamo aperiam speculum.
// Contabesco timidus sollers.”
//
    @Test
    public void test005(){
        response.body("[0].body", equalTo("Depulso auris vereor. Acceptus suffragium repudiandae. Cotidie cubicularis deprecator. Virtus validus aliquid. Adduco somnus quibusdam. Despecto nihil vinum. Claudeo nam ullus. Sursum tutamen rerum. Cenaculum tabula adultus. Charisma thema super. Vobis cavus clibanus. Quo quod avaritia. Condico apparatus nulla. Textilis depopulo acidus."));
}
}

