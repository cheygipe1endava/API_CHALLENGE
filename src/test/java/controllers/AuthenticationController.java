package controllers;

import entities.SessionRequests;
import helpers.JsonHelper;
import io.restassured.response.Response;
import helpers.PropertiesHelper;
import builders.URLBuilder;
import java.net.URL;

public class AuthenticationController extends ApiController{

    private String requestToken;
    private SessionRequests getRequestBody;
    private Response sendRequest;
    /*
    private final String SESSION = "session/";
    private final String NEW = "new?";
    private final String TOKEN = "token";
     */


    public AuthenticationController()
    {
        super();
        //requestSpecification.basePath("authentication/");
        //requestSpecification.queryParam("?api_key=3fb4c73306abfe5787339b5dba7276ba");
    }

    public void sendGuestSession()
    {
        sendRequest = requestSpecification.get(gettingURL("guestSession"));
    }

    public SessionRequests getGuestSession()
    {
        getRequestBody = JsonHelper.responseToRequestsObj(sendRequest);
        return getRequestBody;
    }

    public void getRequestToken()
    {
        getRequestBody = JsonHelper.responseToRequestsObj(requestSpecification.get(gettingURL("token")));
        requestToken = getRequestBody.getRequest_token();
    }

    public void sessionWithLogin()
    {
        getRequestBody = JsonHelper.responseToRequestsObj(requestSpecification.given().body("{\"username\":\"FELIPE_GIRALDO_PEREZ\","
                + "\"password\":\"1234\"," + "\"request_token\":" + "\"" +  requestToken + "\"" + "}")
                .and().post(gettingURL("validateSession")));
        this.requestToken = getRequestBody.getRequest_token();
    }

    public SessionRequests createSession()
    {
        getRequestBody = JsonHelper.responseToRequestsObj(requestSpecification.given().body("{\"request_token\":" + "\"" +
                requestToken + "\"" + "}").and().post(gettingURL("sessionCreation")));
        return getRequestBody;
    }

    public void sendDeleteSession()
    {
        String sessionID = getRequestBody.getSession_id();
        sendRequest = requestSpecification.given().body("{\"session_id\":" + "\"" +  sessionID + "\"" + "}")
                .and().delete(gettingURL("deleteSession"));
    }

    public SessionRequests getdeleteSession()
    {
        getRequestBody = JsonHelper.responseToRequestsObj(sendRequest);
        return getRequestBody;
    }

    public SessionRequests Authenticate() {
        getRequestToken();
        sessionWithLogin();
        createSession();
        return getRequestBody;
    }

    public SessionRequests deleteSession()
    {
        sendDeleteSession();
        getdeleteSession();
        return getRequestBody;
    }
}












