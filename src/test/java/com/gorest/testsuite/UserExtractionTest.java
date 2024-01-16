package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest extends TestBase {
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


//1. Extract the All Ids
@Test
public void extractAllids() {
    List<Integer> ids= response.extract().path("id");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("List of Ids are: " + ids );
    System.out.println("------------------End of Test---------------------------");
}
//2. Extract the all Names
@Test
public void extractAllNames() {
    List<String> names = response.extract().path("names");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("List of names are: " + names );
    System.out.println("------------------End of Test---------------------------");
}
//3. Extract the name of 5th object
@Test
public void extractNameOfproduct() {
    String  name = response.extract().path("data[4].name");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The value of total is : " + name);
    System.out.println("------------------End of Test---------------------------");
}
//4. Extract the names of all object whose status = inactive
@Test
public void extractTheNames(){
    List<String> names = response.extract().path("findAll{it.status == 'inactive'}.name");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Names of all object whose status = inactive are: " + names);
    System.out.println("------------------End of Test---------------------------");
}
//5. Extract the gender of all the object whose status = active
@Test
public void extractTheGender(){
    List<String> gender = response.extract().path("findAll{it.status == 'active'}.gender");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The gender of all the object whose status = active are: " + gender);
    System.out.println("------------------End of Test---------------------------");
}
//6. Print the names of the object whose gender = female
@Test
public void printTheNames(){
    List<String> pNames = response.extract().path("findAll{it.gender = 'female'}.name");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The names of the object whose gender = female are: " + pNames);
    System.out.println("------------------End of Test---------------------------");
}
//7. Get all the emails of the object where status = inactive
@Test
public void getAllTheEmails(){
    List<String> email = response.extract().path("findAll{it.status == 'inactive'}.email");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("All the emails of all object whose status = inactive are: " + email);
    System.out.println("------------------End of Test---------------------------");
}
//8. Get the ids of the object where gender = male
@Test
public void getTheIds(){
    List<Integer> ids = response.extract().path("findAll{it.gender = 'male'}.id");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The ids of the object whose gender = male are: " + ids);
    System.out.println("------------------End of Test---------------------------");
}
//9. Get all the status
@Test
public void getAllTheStatus(){
    List<String> status = response.extract().path("status");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("All the status are: " + status);
    System.out.println("------------------End of Test---------------------------");
}
//10. Get email of the object where name = Avantika Kaur
@Test
public void getEmail(){
    String email = response.extract().path("find{it.name == 'Avantika Kaur'}.email");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Email of the object where name = Avantika Kaur : " + email);
    System.out.println("------------------End of Test---------------------------");
}
//11. Get gender of id = 5914158
@Test
public void getGender(){
    String gender = response.extract().path("[1].gender");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Gender of id = 5914158 is : " + gender);
    System.out.println("------------------End of Test---------------------------");
}
//
}
