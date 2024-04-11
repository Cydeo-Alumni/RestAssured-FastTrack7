package com.cydeo.day02;

import com.cydeo.pojo.CategoryPOST;
import com.cydeo.utility.FakeStoreTestBase;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
public class P02_SerializationJsonFile extends FakeStoreTestBase {


    @Test
    public void task1() throws IOException {

        // SEND DATA FROM JSON FILE
        JsonPath jp = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(new File("src/test/resources/test-data/category.json")).
                when().post("/categories").
                then().statusCode(201)
                .extract().jsonPath();

        // Verify data name is correct ?
        // Response name

        String actualName = jp.getString("name");

        // Retrieve name from json file
        ObjectMapper objectMapper = new ObjectMapper();


        // POJO
        CategoryPOST category = objectMapper.readValue(new File("src/test/resources/test-data/category.json"), CategoryPOST.class);

        System.out.println("Category json file content as followings  = " + category);


        // MAP
        Map<String,String> mapCategory=objectMapper.readValue(new File("src/test/resources/test-data/category.json"), Map.class);

        System.out.println("Category json file content as followings  = " + mapCategory);

        String expectedCategory = category.getName();
        assertEquals(expectedCategory,actualName);

        // WRITE THE BODY THAT YOU RETRIVE FROM RESPONSE TO A JSON FILE
        CategoryPOST categoryResponse = jp.getObject("", CategoryPOST.class);
        System.out.println("categoryResponse = " + categoryResponse);

        objectMapper.writeValue(new File("src/test/resources/test-data/post_category.json"),categoryResponse);

    }
}
