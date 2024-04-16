package com.cydeo.day03;

import com.cydeo.utility.BookitTestBase;
import com.cydeo.utility.BookitUtil;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class P03_BookitTest extends BookitTestBase {

          /*
            .get("/api/campuses")

    credentials:
         String email="lfinnisz@yolasite.com";
         String password="lissiefinnis";


        Task 1 :Deserialization Exercise
                send a request to get all campuses
                verify status code 200
                Create an POJO to get all data
                        //Find out how many room  we have in light-side
                        //Find out how many cluster we have in VA
                        //Find out how many campus we have
     */

    @Test
    public void task1() {

        // Get Token
        String email=System.getenv("B_USERNAME");
        System.out.println("email = " + email);
        String password=System.getenv("B_PASS");
        System.out.println("password = " + password);
        String token = BookitUtil.getToken(email, password);

        System.out.println("token = " + token);

        // Get api/campuses
        JsonPath jp = given().accept(ContentType.JSON)
                .header("Authorization", token).
                when().get("/api/campuses").prettyPeek().
                then().statusCode(200)
                .extract().jsonPath();

        /*
            JSON - POJO

            //Find out how many room  we have in light-side
            //Find out how many cluster we have in VA
            //Find out how many campus we have

         */

    }


}
