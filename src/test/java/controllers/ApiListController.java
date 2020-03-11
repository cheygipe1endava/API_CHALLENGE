package controllers;

import helpers.JsonHelper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.yecht.Data;

public class ApiListController {
    protected RequestSpecification requestSpecification;
    protected final String API_KEY = "api_key";
    protected String addedMovieID;

    public ApiListController() {
        System.setProperty("https.protocols", "TLSv1.2");
        this.requestSpecification = RestAssured.given().contentType(ContentType.JSON);
        requestSpecification.baseUri("https://api.themoviedb.org/3/");
    }

    public void setMovieIDController(String movieID)
    {
        addedMovieID = movieID;
    }

    public String gettingBodies(String body){
        switch (body){
            case "listCreation":
                return "{\"name\":\"Mock List Challenge Correction\"," +
                        "\"description\":\"\"," + "\"language\":\"en\"}";
            case "addedMovie":
                return "{\"media_id\": " + addedMovieID + "}";
            default:
        }
        return null;
    }
}
