package com.cydeo.day02;

import com.cydeo.pojo.CategoryPOST;
import com.cydeo.utility.FakeStoreTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
public class P01_SerializationFlow extends FakeStoreTestBase {

/*
    Given accept header is application json
    And content type is application json
    And requestBody field and values are
        |name                 |image  |
        |My Category          |url  |
    When I send POST request /categories endpoint
    Then status code should be 201
    And response body should have posted product information
    And id field should exist
 */

    @Test
    public void post() {

    /*
        - String   -- X
        - Java --> MAP / OBJECT ( POJO )
        - JSON File

     */

        CategoryPOST category=new CategoryPOST();
        category.setName("Nice Product");
        category.setImage("https://t4.ftcdn.net/jpg/00/81/38/59/360_F_81385977_wNaDMtgrIj5uU5QEQLcC9UNzkJc57xbu.jpg");

        /*
        Map<String,String> catMap=new HashMap<>();
        catMap.put("name","Nice Product");
        catMap.put("image","https://t4.ftcdn.net/jpg/00/81/38/59/360_F_81385977_wNaDMtgrIj5uU5QEQLcC9UNzkJc57xbu.jpg");
        */

        System.out.println("POST /categories end point is working");
        System.out.println("Category object is created with following data");
        System.out.println(category);

        JsonPath jp = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(category).
                when().post("/categories").then()
                .statusCode(201)
                .contentType("application/json; charset=utf-8")
                .body("id", is(notNullValue()))
                .extract().jsonPath();

        // ASSERTION FOR NAME
        String name = jp.getString("name");
        assertEquals(category.getName(),name);

        // ASSERTION FOR URL
        String image = jp.getString("image");
        assertEquals(category.getImage(),image);

        // ID
        int id = jp.getInt("id");
        System.out.println("Category is generated with following id "+id);


    }

    @Test
    public void getCategory() {

        System.out.println("-- id is retrived from previous execution to make sure category is generated"+127);

        given().accept(ContentType.JSON)
                .pathParam("id",127).
        when().get("/categories/{id}").
        then().statusCode(200)
                .body("name",is("Nice Product"));


        System.out.println("-- GET /categories/{id} endpoint worked and name is verified");


    }


    @Test
    public void delete() {
        System.out.println("-- id is retrived from previous execution to delete this product"+180);

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 209).
                when().delete("/categories/{id}")
                .then()
                .statusCode(200)
                .extract().response();


        String responseTrue = response.asString();
        System.out.println(responseTrue);
        assertEquals("true",responseTrue);


        System.out.println("-- DELETE /categories/{id} endpoint worked with following id"+180);


    }
}
