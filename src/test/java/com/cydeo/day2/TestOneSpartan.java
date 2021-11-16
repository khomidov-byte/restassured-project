package com.cydeo.day2;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
public class TestOneSpartan {

    // Get http://34.229.82.244:8000/api/spartans/1

    @Test
    public void testOneSpartan(){
        // sending a get request to this url and saving the response into Response object
        Response response = get("http://34.229.82.244:8000/api/spartans/1");

        // print out the status code
        // statusCode() and getStatusCode does same thing to return status of resposne
        int statusCode =  response.statusCode() ;
        System.out.println("statusCode = " + statusCode);


        // one way to get the body as string and print it out without sout
        response.prettyPrint();
        // another way to get the body as string is asString , it does not print for you , you print yourself
        System.out.println("response.asString() = " + response.asString() );

        // getting header from the response
        // using header("header name") or getHeader("header name") methods , do same exact thing
        System.out.println("response.header(\"Content-Type\") = "
                + response.header("Content-Type") );
        // this is the example of using getHeader method that does same thing
        System.out.println("response.getHeader(\"Content-Type\") = "
                + response.getHeader("Content-Type"));

        // try to get Date Header , Keep-Alive header , Connection
        // IF YOU PROVIDE WRONG HEADER NAME , IT WILL RETURN NULL
        System.out.println("response.header(\"Date\") = " + response.header("Date") );
        System.out.println("response.header(\"Keep-Alive\") = " + response.header("Keep-Alive") );
        System.out.println("response.getHeader(\"Connection\") = " + response.getHeader("Connection"));

    }

    @Test
    public void testContentTypeHeader(){
        // sending a get request to this url and saving the response into Response object
        Response response = get("http://34.229.82.244:8000/api/spartans/1");

        // RestAssured has special support for common header like content type with method
        // below two line does exact same thing as response.header("Content-Type")
        // it's just easier way to do it without typing much
        System.out.println("response.contentType() = " + response.contentType() );
        System.out.println("response.getContentType() = " + response.getContentType() );

        // write an assertion to verify contentType() = application/json
        Assertions.assertEquals("application/json" ,  response.contentType() ) ;

        // Different type of content type is represented in ENUM coming from
        // import io.restassured.http.ContentType;
        System.out.println("ContentType.JSON = " + ContentType.JSON);
        System.out.println("ContentType.XML = " + ContentType.XML);
        System.out.println("ContentType.TEXT = " + ContentType.TEXT);
        System.out.println("ContentType.URLENC = " + ContentType.URLENC);

        // now we can simply just replace the string with enum to avoid any types
        // ContentType.JSON return enum, so we need to convert to string before comparison
        Assertions.assertEquals(ContentType.JSON.toString()  ,  response.contentType() )  ;


    }

    @Test
    public void testJSONBody(){

        /**
         * {
         *     "id": 1,
         *     "name": "Who is King",
         *     "gender": "Male",
         *     "phone": 1112223344
         * }
         */
        // sending a get request to this url and saving the response into Response object
        Response response = get("http://34.229.82.244:8000/api/spartans/1");
        // print out the body
        response.prettyPrint() ;
        // just like navigating through html using xpath to get to certain element
        // you can navigate through json to get the value of certain key using jsonpath
        // the easiest way to get the value using jsonpath is using path method from response objet
        // jsonpath to get the id value is just "id" because it's top level element

        System.out.println("response.path(\"id\") = " + response.path("id") );
        System.out.println("response.path(\"name\") = " + response.path("name") );
        System.out.println("response.path(\"gender\") = " + response.path("gender") );
        System.out.println("response.path(\"phone\") = " + response.path("phone") );



    }







}


