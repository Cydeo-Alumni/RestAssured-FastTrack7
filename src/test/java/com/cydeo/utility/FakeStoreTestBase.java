package com.cydeo.utility;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class FakeStoreTestBase {

    @BeforeAll
    public static void init(){

        RestAssured.baseURI="https://api.escuelajs.co/api/v1";

    }

}
