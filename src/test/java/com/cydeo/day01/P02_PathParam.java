package com.cydeo.day01;

import com.cydeo.utility.FakeStoreTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class P02_PathParam extends FakeStoreTestBase {
    /**
     *1- Given accept type is Json
     *2- Path Parameters value is
     *     - id —> 13
     *3- When user sends GET request to api/v1/products/{id}
     *4- Verify followings
     *     - Status code should be 200
     *     - Content Type is application/json; charset=utf-8
     *     - Print response
     *     - id is 13
     *     - Name is "Classic Olive Chino Shorts"
     *     - Category name is "Clothes"
     */
    @Test
    public void responsePath() {

        Response response = RestAssured.given().accept(ContentType.JSON)
                .pathParam("id", 13)
                .when().get("/products/{id}");

        //     *     - Print response
        response.prettyPrint();

        //     *     - Status code should be 200

        Assertions.assertEquals(200,response.statusCode());

        //     *     - Content Type is application/json; charset=utf-8

        Assertions.assertEquals("application/json; charset=utf-8",response.contentType());

        //     *     - id is 13
        int id = response.path("id");
        Assertions.assertEquals(13,id);

        //     *     - Name is "Classic Olive Chino Shorts"
        Assertions.assertEquals("Classic Olive Chino Shorts",response.path("title"));

        //     *     - Category name is "Clothes"
        Assertions.assertEquals("Clothes",response.path("category.name"));


    }


}
