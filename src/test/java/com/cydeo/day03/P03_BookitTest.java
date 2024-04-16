package com.cydeo.day03;

import com.cydeo.pojo.Campus;
import com.cydeo.pojo.Cluster;
import com.cydeo.pojo.Room;
import com.cydeo.utility.BookitTestBase;
import com.cydeo.utility.BookitUtil;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.List;

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
        String email=System.getenv("B_EMAIL");
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

        List<Campus> allCampuses = jp.getList("", Campus.class);
        for (Campus eachCampus : allCampuses) {
            System.out.println("eachCampus = " + eachCampus);
        }

        //Find out how many campus we have
        System.out.println("allCampuses.size() = " + allCampuses.size());

        //Find out how many cluster we have in VA
        Campus firstCampus = allCampuses.get(0);
        List<Cluster> allClusterInFirst = firstCampus.getClusterList();
        System.out.println("allClusterInFirst.size() = " + allClusterInFirst.size());

        //Find out how many room  we have in light-side
        Cluster firstCluster = allClusterInFirst.get(0);
        List<Room> allRooms = firstCluster.getRoomList();
        System.out.println("allRooms.size() = " + allRooms.size());


    }


}
