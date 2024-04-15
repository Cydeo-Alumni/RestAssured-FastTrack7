package com.cydeo.utility;

import io.restassured.RestAssured;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;

public abstract class FakeStoreTestBase {

    Logger log= LogManager.getLogger();

    @BeforeAll
    public static void init(){

        RestAssured.baseURI="https://api.escuelajs.co/api/v1";

    }

}
