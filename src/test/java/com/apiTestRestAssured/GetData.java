package com.apiTestRestAssured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;

import static io.restassured.RestAssured.*;
import static io.restassured.parsing.Parser.JSON;

public class GetData {

    @Test
    public void listverification() {

        RestAssured.baseURI = "https://api.github.com/";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, "/gists/public");

        String responseBody = response.getBody().asString();
        System.out.println("Resonse Body is:" + responseBody);

        int statusCode = response.getStatusCode();
        System.out.println("Status code is:" + statusCode);
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void logInTest() {


        int code = RestAssured.given()
                .auth().preemptive()
                .basic("vvkishor", "Mymilky1")
                .when()
                .get("https://api.github.com/user")
                .getStatusCode();
        System.out.println("Response Code from the Server is" + code);
        Assert.assertEquals(code, 200);
    }

   @Test
    public void createGist() {

       int code = RestAssured.given()
                    .auth().preemptive()
                    .basic("vvkishor", "Mymilky1")
                    .when()
                    .get("https://api.github.com/gists")
                    .getStatusCode();

       JSONObject json = new JSONObject();
       json.put("files","Question3Test");
       json.put("description","fromApiChannel");
       json.put("public","true");

       System.out.println("Response Code from the Server is :" + code);
       Assert.assertEquals(code, 200);


    }
    @Test
    public void deleteGist() {

        RestAssured.baseURI = "https://www.api.github.com/";
        given().urlEncodingEnabled(true)
                .param("username", "vvkishor")
                .param("password", "Mymilky1")
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .post("/delete/gists/gist_id")
                .then().statusCode(405);
    }



}



