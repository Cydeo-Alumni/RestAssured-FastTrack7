package com.cydeo.utility;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class BookitUtil {


    public static String getToken(String email,String password){

        JsonPath jp = given().log().uri().
                accept(ContentType.JSON)
                .queryParam("email", email)
                .queryParam("password", password)
                .baseUri("https://api.qa.bookit.cydeo.com").
                when().get("/sign").
                then().statusCode(200)
                .extract().jsonPath();

        String accessToken = jp.getString("accessToken");


        return "Bearer "+accessToken;
    }


}
