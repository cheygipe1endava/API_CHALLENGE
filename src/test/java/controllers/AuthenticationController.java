package controllers;

import entities.SessionRequests;
import helpers.JsonHelper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import helpers.PropertiesHelper;
import builders.URLBuilder;
import java.net.URL;


public class AuthenticationController extends ApiController{

    private String requestToken;
    private String sessionID;
    private String sessionWithLoginBody;
    private SessionRequests getRequestBody, requests, sessionRequest;
    /*
    private final String apiKey = "api_key=3fb4c73306abfe5787339b5dba7276ba";
    private final String SESSION = "session/";
    private final String NEW = "new?";
    private final String TOKEN = "token";
     */

    public AuthenticationController() {
        super();
        RestAssured.basePath="authentication/";
    }

    public String GuestSession()
    {
        URL idUrl = new URLBuilder()
                .addDomain(PropertiesHelper.getValueByKey("url.base"))
                .addPathStep("authentication/guest_session/new")
                .build();
/*
        URI builder = new URIBuilder("https://api.themoviedb.org")
                                             .setScheme("https").setHost("api.themoviedb.org")
                                             .setPath("authentication/guest_session/new")
                                             .setQuery("api_key=3fb4c73306abfe5787339b5dba7276ba")
                                             .build().toString();
 */
        Response response = requestSpecification.get(idUrl);
        requests = JsonHelper.responseToRequestsObj(response);
        String a = response.getBody().asString();
        return requests.getGuest_session_id();

    }

    public URL gettingURL(String endpoint){
        switch (endpoint){
            case "token":
                return new URLBuilder()
                        .addDomain(PropertiesHelper.getValueByKey("url.base"))
                        .addPathStep("authentication/token/new")
                        .build();
            case "validate":
                return new URLBuilder()
                        .addDomain(PropertiesHelper.getValueByKey("url.base"))
                        .addPathStep("authentication/token/validate_with_login")
                        .build();
            case "session":
                return new URLBuilder()
                        .addDomain(PropertiesHelper.getValueByKey("url.base"))
                        .addPathStep("authentication/session/new")
                        .build();
            default:
        }
        return null;
    }

    public void sessionWithLoginBody()
    {
        sessionWithLoginBody = "{\"username\":\"FELIPE_GIRALDO_PEREZ\"," +
                "\"password\":\"1234\"," +
                "\"request_token\":" + "\"" +  requestToken + "\"" + "}";
    }

    public void createSessionBody()
    {
        sessionWithLoginBody = "{\"request_token\":" + "\"" +  requestToken + "\"" + "}";
    }

    public void getRequestToken()
    {
        getRequestBody = JsonHelper.responseToRequestsObj(requestSpecification.get(gettingURL("token")));
        requestToken = getRequestBody.getRequest_token();
    }

    public void sessionWithLogin()
    {
        Response sendRequest = requestSpecification.given().body(sessionWithLoginBody)
                                .and().post(gettingURL("validate"));
        getRequestBody = JsonHelper.responseToRequestsObj(sendRequest);
    }

    public String createSession()
    {
        Response sendRequest = requestSpecification.given().body(sessionWithLoginBody)
                                .and().post(gettingURL("session"));
        sessionRequest = JsonHelper.responseToRequestsObj(sendRequest);
        //sessionID = sessionRequest.getSession_id();
        return sessionRequest.getSuccess();
    }

    public String returnSessionID()
    {
        sessionID = sessionRequest.getSession_id();
        return sessionID;
    }

    public String DeleteSession()
    {
        String SessionBody = "{\"session_id\":" + "\"" +  sessionID + "\"" + "}";

        URL idUrl = new URLBuilder()
                .addDomain(PropertiesHelper.getValueByKey("url.base"))
                .addPathStep("authentication/session")
                .build();

        Response response = requestSpecification.given().body(SessionBody).and().delete(idUrl);
        requests = JsonHelper.responseToRequestsObj(response);
        return requests.getSuccess();
    }



}












