package com.cydeo.day03;

import com.cydeo.pojo.DriverPOJO;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class P02_XMLPractice {
         /*
          - Given accept type is application/xml
          - When user sends GET request to "/drivers" endpoint
          - Then status code should be 200
                 - first Driver Given Name
                 - all Drivers Given Name
                 - print out first driver driverId attribute
                 - print out all driver driverId attribute
                 - Print out all driver given name if their nationality is Italian

   */

    @Test
    public void xmlPractice() {

        Response response = given().log().uri()
                .baseUri("http://ergast.com/api/f1").
                when().get("/drivers").
                then().statusCode(200)
                .extract().response();

        XmlPath xmlPath = response.xmlPath();

        // - first Driver Given Name
        String firstGivenName = xmlPath.getString("MRData.DriverTable.Driver[0].GivenName");
        System.out.println("firstGivenName = " + firstGivenName);

        // - all Drivers Given Name
        List<String> allGivenNames = xmlPath.getList("MRData.DriverTable.Driver.GivenName");
        System.out.println("allGivenNames = " + allGivenNames);

        // - print out first driver driverId attribute
        String firstDriverID = xmlPath.getString("MRData.DriverTable.Driver[0].@driverId");
        System.out.println("firstDriverID = " + firstDriverID);


        // - print out all driver driverId attribute
        List<String> allDriverIDs = xmlPath.getList("MRData.DriverTable.Driver.@driverId");
        System.out.println("allDriverIDs = " + allDriverIDs);


        // - Print out all driver given name if their nationality is Italian
        List<String> allItalians = xmlPath.getList("MRData.DriverTable.Driver.findAll {it.Nationality=='Italian'}.GivenName");
        System.out.println("allItalians = " + allItalians);

        // do it with loop
        int allDriverSize = allGivenNames.size();


        for (int i = 0; i < allDriverSize; i++) {
          // Get me Nationality
            String nationality = xmlPath.getString("MRData.DriverTable.Driver["+i+"].Nationality");
            if(nationality.equals("Italian")){
                String givenName = xmlPath.getString("MRData.DriverTable.Driver["+i+"].GivenName");
                System.out.println(givenName);
                // Give the GivenName of Drivers
            }
        }


        // do with pojo
        List<DriverPOJO> allDrivers = new ArrayList<>();

        for (int i = 0; i < allDriverSize; i++) {

            DriverPOJO driver=new DriverPOJO();

            String givenName = xmlPath.getString("MRData.DriverTable.Driver["+i+"].GivenName");
            driver.setGivenName(givenName);

            String familyName = xmlPath.getString("MRData.DriverTable.Driver["+i+"].FamilyName");
            driver.setFamilyName(familyName);

            String nationality = xmlPath.getString("MRData.DriverTable.Driver["+i+"].Nationality");
            driver.setNationality(nationality);

            /*

            String dob = xmlPath.getString("MRData.DriverTable.Driver["+i+"].DateOfBirth");
            driver.setDob(dob);

             */

            allDrivers.add(driver);

        }

        for (DriverPOJO eachDriver : allDrivers) {
            if(eachDriver.getNationality().equals("Italian")){
                System.out.println("eachDriver.getGivenName() = " + eachDriver.getGivenName());
            }
        }


    }
}
