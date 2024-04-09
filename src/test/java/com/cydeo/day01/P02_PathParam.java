package com.cydeo.day01;

import com.cydeo.utility.FakeStoreTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class P02_PathParam extends FakeStoreTestBase {
    /**
     * 1- Given accept type is Json
     * 2- Path Parameters value is
     * - id —> 13
     * 3- When user sends GET request to api/v1/products/{id}
     * 4- Verify followings
     * - Status code should be 200
     * - Content Type is application/json; charset=utf-8
     * - Print response
     * - id is 13
     * - Name is "Classic Olive Chino Shorts"
     * - Category name is "Clothes"
     */
    @Test
    public void responsePath() {

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 13)
                .when().get("/products/{id}");

        //     *     - Print response
        response.prettyPrint();

        //     *     - Status code should be 200

        assertEquals(200, response.statusCode());

        //     *     - Content Type is application/json; charset=utf-8

        assertEquals("application/json; charset=utf-8", response.contentType());

        //     *     - id is 13
        int id = response.path("id");
        assertEquals(13, id);

        //     *     - Name is "Classic Olive Chino Shorts"
        assertEquals("Classic Olive Chino Shorts", response.path("title"));

        //     *     - Category name is "Clothes"
        assertEquals("Clothes", response.path("category.name"));


        // Verify category.imageurl link is working
        String imgUrl = response.path("category.image");
        System.out.println("imgUrl = " + imgUrl);

        int statusCode = RestAssured.get(imgUrl).statusCode();
        assertEquals(200, statusCode);

    }


    @Test
    public void jsonPath() {

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 13)
                .when().get("/products/{id}");


        //     *     - Print response
       response.prettyPrint();

        //     *     - Status code should be 200
        assertEquals(200,response.statusCode());

        //     *     - Content Type is application/json; charset=utf-8
        assertEquals("application/json; charset=utf-8",response.contentType());

        // CREATE A JSONPATH OBJECT
        JsonPath jp = response.jsonPath();
        //     *     - id is 13
        int id = jp.getInt("id");
        assertEquals(13,id);

        //     *     - Name is "Classic Olive Chino Shorts"
        assertEquals("Classic Olive Chino Shorts",jp.getString("title"));

        //     *     - Category name is "Clothes"
        assertEquals("Clothes",jp.getString("category.name"));

    }

    @Test
    public void hamCrest() {

        given().accept(ContentType.JSON)
                .pathParam("id", 13)
                .when().get("/products/{id}").prettyPeek()
                .then()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .body("id", is(13))
                .body("title",is("Classic Olive Chino Shorts"))
                .body("category.name",is("Clothes"));


    }
}
