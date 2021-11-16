package com.cydeo.day2;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class TestOneSpartan2 {

    //  GET http://54.236.150.168:8000/api/spartans/1

    // We can breakdown above url to 3 part to tell RestAssured to append at the end of our endpoints

    /**
     * BaseURI  : http://54.236.150.168:8000
     * BasePath : /api
     * Anything comes after actual resources
     *
     * For RestAssured The whole URL will be BaseURI + BasePath + whatever you put in get("here")
     */
    @BeforeAll
    public static void setup(){
        RestAssured.baseURI = "http://54.236.150.168:8000" ;
        RestAssured.basePath = "/api";
    }

    @AfterAll
    public static void teardown(){
        // in order to avoid the static value accidentally carried over
        // to different class when we practice different api ,
        // it's better if we set baseURI basePath back to it's original value using reset method
        //RestAssured.reset();
        reset();  //

    }

    // add a new test to check GET api/hello  Endpoint
    // verify status code is 200 , content type is text/plain , body is Hello from Sparta (this is string not json)
    @Test
    public void testHelloAgain(){
        // the actual url being used is baseURI + basePath + /hello
        Response response = get("/hello") ;

        Assertions.assertEquals(200,  response.statusCode() );
        // this did not work because actual response had extra text at the end text/plain;charset=UTF-8
        //Assertions.assertEquals( ContentType.TEXT.toString() ,   response.contentType()    );
        // we will just use string instead
        Assertions.assertEquals( "text/plain;charset=UTF-8",   response.contentType()    );
        Assertions.assertEquals( "Hello from Sparta",  response.asString()   );


    }




    @Test
    public void testOneSpartan(){

        // sending a get request to this url and saving the response into Response object
        //  Your final url that actually sent is baseURI + basePath + /spartans/1
        Response response = get("/spartans/1");

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
        Response response = get("/spartans/1");

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
        Response response = get("/spartans/1");
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
        /** output
         *  response.path("id") = 1
         *  response.path("name") = Who is King
         *  response.path("gender") = Male
         *  response.path("phone") = 1112223344
         */
        // save the json value you got from the key into variables
        int myId        =  response.path("id") ;
        String myName   = response.path("name") ;
        String myGender = response.path("gender") ;
        long myPhone    = response.path("phone");

        System.out.println("myId = " + myId);
        System.out.println("myName = " + myName);
        System.out.println("myGender = " + myGender);
        System.out.println("myPhone = " + myPhone);

        // write assertions to verify the json body
        Assertions.assertEquals(1 , myId);
        Assertions.assertEquals("Who is King" , myName);
        Assertions.assertEquals("Male" , myGender);
        Assertions.assertEquals(11122233440L , myPhone);




    }







}