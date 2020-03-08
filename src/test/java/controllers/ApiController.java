package controllers;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.net.URL;

public class ApiController {
    protected RequestSpecification requestSpecification;

    public ApiController() {
        this.requestSpecification = RestAssured.given().contentType(ContentType.JSON);
        RestAssured.baseURI="https://api.themoviedb.org/3/";
    }
}
