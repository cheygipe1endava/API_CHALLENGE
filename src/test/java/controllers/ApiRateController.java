package controllers;

import builders.URLBuilder;
import helpers.PropertiesHelper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.net.URL;

public class ApiRateController {
    protected RequestSpecification requestSpecification;
    protected final String API_KEY = "api_key";
    protected String requestToken, sessionID;
    protected double rate;

    public ApiRateController() {
        System.setProperty("https.protocols", "TLSv1.2");
        this.requestSpecification = RestAssured.given().contentType(ContentType.JSON);
        requestSpecification.baseUri("https://api.themoviedb.org/3/");
    }

    public void setRatingController(double rating)
    {
        rate = rating;
    }

    public String gettingBody(String body){
        if (body == "rate")
        {
            return "{\"value\": " + rate + "}";
        }
        return null;
    }
}
