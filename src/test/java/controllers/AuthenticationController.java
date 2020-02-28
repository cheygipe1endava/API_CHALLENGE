package controllers;

import entities.SessionRequests;
import helpers.JsonHelper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import helpers.PropertiesHelper;
import builders.URLBuilder;


import java.net.URL;


public class AuthenticationController {

    String reqToken = " ", sessionID= " ", guestSession= " ", deleteSession= " ";

    private SessionRequests requests, sessionRequest;

    private RequestSpecification httpRequestInstance = RestAssured.given().contentType(ContentType.JSON);

    public AuthenticationController() {
    }

    public SessionRequests Authenticate() {
        GuestSession();
        TakeAPIResponse();
        SessionWithLogin();
        CreateSession();
        return sessionRequest;
    }

    public String GuestSession()
    {
        URL idUrl = new URLBuilder()
                .addDomain(PropertiesHelper.getValueByKey("url.base"))
                .addPathStep("authentication/guest_session/new")
                .addQuery(PropertiesHelper.getValueByKey("url.query"))
                .build();

        Response response = httpRequestInstance.get(idUrl);
        requests = JsonHelper.responseToRequestsObj(response);
        String a = response.getBody().asString();
        guestSession = requests.getGuest_session_id();
        return guestSession;

    }

    public void TakeAPIResponse() {
        URL idUrl = new URLBuilder()
                .addDomain(PropertiesHelper.getValueByKey("url.base"))
                .addPathStep("authentication/token/new")
                .addQuery(PropertiesHelper.getValueByKey("url.query"))
                .build();

        Response response = httpRequestInstance.get(idUrl);
        requests = JsonHelper.responseToRequestsObj(response);
        reqToken = requests.getRequest_token();
    }

    public void SessionWithLogin() {


        String SessionBody = "{\"username\":\"FELIPE_GIRALDO_PEREZ\"," +
                "\"password\":\"1234\"," +
                "\"request_token\":" + "\"" +  reqToken + "\"" + "}";

        URL idUrl = new URLBuilder()
                .addDomain(PropertiesHelper.getValueByKey("url.base"))
                .addPathStep("authentication/token/validate_with_login")
                .addQuery(PropertiesHelper.getValueByKey("url.query"))
                .build();

        Response response = httpRequestInstance.given().body(SessionBody).and().post(idUrl);
        requests = JsonHelper.responseToRequestsObj(response);
        reqToken = requests.getRequest_token();
    }

    public SessionRequests CreateSession()
    {
        String SessionBody = "{\"request_token\":" + "\"" +  reqToken + "\"" + "}";

        URL idUrl = new URLBuilder()
                .addDomain(PropertiesHelper.getValueByKey("url.base"))
                .addPathStep("authentication/session/new")
                .addQuery(PropertiesHelper.getValueByKey("url.query"))
                .build();

        Response response = httpRequestInstance.given().body(SessionBody).and().post(idUrl);
        sessionRequest = JsonHelper.responseToRequestsObj(response);
        sessionID = sessionRequest.getSession_id();
        return sessionRequest;
    }

    public String DeleteSession()
    {
        String SessionBody = "{\"session_id\":" + "\"" +  sessionID + "\"" + "}";

        URL idUrl = new URLBuilder()
                .addDomain(PropertiesHelper.getValueByKey("url.base"))
                .addPathStep("authentication/session")
                .addQuery(PropertiesHelper.getValueByKey("url.query"))
                .build();

        Response response = httpRequestInstance.given().body(SessionBody).and().delete(idUrl);
        requests = JsonHelper.responseToRequestsObj(response);
        deleteSession = requests.getSuccess();
        return deleteSession;
    }


}












