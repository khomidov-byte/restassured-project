package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.List;

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


    @Test
    public void testGetXMLResponse(){

       Response response =  given().header("Accept", "application/xml").when().get("/spartans");
        response.prettyPrint();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(ContentType.XML.toString(), response.contentType());




    }


    // SEND REQUEST TO GET http://54.236.150.168:8000/api/spartans/search?nameContains=Ea&gender=Male
    @Test
    public void testSearch(){

        Response response = given().queryParam("nameContains", "Ea")
                .queryParam("gender", "Male").when().get("/spartans/search");

        response.prettyPrint();

        System.out.println("response.path(\"totalElement\") = " + response.path("totalElement"));

        // get the first person name ,
        System.out.println("response.path(\"content[0].name\") = "
                + response.path("content[0].name") );

        System.out.println("response.path(\"content.name[0]\") = "
                + response.path("content.name[0]"));
        
        List<String> allNames = response.path("content.name");
        System.out.println("allNames = " + allNames);


    }

    @Test
    public void testOneSpartanPathParam(){

        Response response = given().pathParam("id", 1).when().get("/spartans/{id}");
        response.prettyPrint();

    }






}