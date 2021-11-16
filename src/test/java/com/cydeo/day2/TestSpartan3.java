package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class TestSpartan3 {

    // set up and teardown
    @BeforeAll
    public static void setup(){
        RestAssured.baseURI = "http://54.236.150.168:8000" ;
        RestAssured.basePath = "/api";
    }

    @AfterAll
    public static void teardown(){
        reset();
    }

    // Send request to GET /spartans and check status code 200 and content type json
    @Test
    public void testAllSpartan(){

        Response response = get("/spartans");
        response.prettyPrint() ;  // print the payload and return it as string at the same time.

        Assertions.assertEquals(200,  response.statusCode()  );

        Assertions.assertEquals(ContentType.JSON.toString() ,  response.contentType()     );

        System.out.println("response.path(\"[0].gender\") = " + response.path("[0].gender"));

        System.out.println("response.path(\"gender[0]\") = " + response.path("gender[0]"));


    }




}