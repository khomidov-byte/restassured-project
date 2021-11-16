package com.cydeo.day1;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;

public class TestSpartanAPI {


    @Test
    public void testHello(){
        System.out.println("Hello world");

        // send a request to below request url and save the response
        // GET http://34.229.82.244:8000/api/hello
        //RestAssured.get("http://34.229.82.244:8000/api/hello");
        // get method is coming from RestAssured class to send get request to the url defined
        // the result of sending request can be stored in Response object coming from RestAssured
        // Type Response is coming from import io.restassured.response.Response;


        Response response = get("http://34.229.82.244:8000/api/hello");
        System.out.println("response.statusCode() = " + response.statusCode());

        // there are many ways to print the response, easies one will be prettyPrint
        response.prettyPrint();
        // assert status code is 200
        Assertions.assertEquals(200,  response.statusCode() );


    }
}
