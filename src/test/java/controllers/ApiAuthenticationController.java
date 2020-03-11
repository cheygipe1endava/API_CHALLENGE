package controllers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class ApiAuthenticationController {
    protected RequestSpecification requestSpecification;
    protected final String API_KEY = "api_key";
    protected String requestToken, sessionID;

    public ApiAuthenticationController() {
        System.setProperty("https.protocols", "TLSv1.2");
        this.requestSpecification = RestAssured.given().contentType(ContentType.JSON);
        requestSpecification.baseUri("https://api.themoviedb.org/3/");
    }

    public void setRequestToken(String requestToken)
    {
        this.requestToken = requestToken;
    }

    public void setSessionID(String sessionID)
    {
        this.sessionID = sessionID;
    }

    public String gettingBodies(String body){
        switch (body){
            case "sessionWithLogin":
                return "{\"username\":\"FELIPE_GIRALDO_PEREZ\","
                        + "\"password\":\"1234\"," + "\"request_token\":" + "\"" +  requestToken + "\"" + "}";
            case "createSession":
                return "{\"request_token\":" + "\"" + requestToken + "\"" + "}";
            case "deleteSession":
                return "{\"session_id\":" + "\"" + sessionID + "\"" + "}";
            default:
        }
        return null;
    }
}
