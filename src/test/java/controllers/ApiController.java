package controllers;


import builders.URLBuilder;
import helpers.PropertiesHelper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.net.URL;

public class ApiController {
    protected RequestSpecification requestSpecification;

    public ApiController() {
        System.setProperty("https.protocols", "TLSv1.2");
        this.requestSpecification = RestAssured.given().contentType(ContentType.JSON);
        //requestSpecification.baseUri("https://api.themoviedb.org/3/");
        //RestAssured.baseURI="https://api.themoviedb.org/3/";
    }

    public URL gettingURL(String endpoint){
        switch (endpoint){
            case "token":
                return new URLBuilder()
                        .addDomain(PropertiesHelper.getValueByKey("url.base"))
                        .addPathStep("authentication/token/new")
                        .addQuery("")
                        .build();
            case "validateSession":
                return new URLBuilder()
                        .addDomain(PropertiesHelper.getValueByKey("url.base"))
                        .addPathStep("authentication/token/validate_with_login")
                        .addQuery("")
                        .build();
            case "sessionCreation":
                return new URLBuilder()
                        .addDomain(PropertiesHelper.getValueByKey("url.base"))
                        .addPathStep("authentication/session/new")
                        .addQuery("")
                        .build();
            default:
        }
        return null;
    }

}
