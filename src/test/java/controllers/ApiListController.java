package controllers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class ApiListController {
    protected RequestSpecification requestSpecification;
    protected final String API_KEY = "api_key";

    public ApiListController() {
        System.setProperty("https.protocols", "TLSv1.2");
        this.requestSpecification = RestAssured.given().contentType(ContentType.JSON);
        requestSpecification.baseUri("https://api.themoviedb.org/3/");
    }
}
