package controllers;

import cucumber.api.java.sk.Tak;
import entities.Requests;
import helpers.JsonHelper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import helpers.PropertiesHelper;
import builders.URLBuilder;
import org.jruby.RubyProcess;
import org.json.JSONObject;
import org.yecht.Data;

import java.net.URL;



public class AuthenticationController {

    String reqToken, sessionID, guestSession, deleteSession;

    private Response response;
    private Requests requests, sessionRequest;
    private Requests requi;
    private RequestSpecification httpRequestInstance = RestAssured.given().contentType(ContentType.JSON);

    public AuthenticationController() {
    }

    public Requests Authenticate() {
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
                .addQuery("api_key=3fb4c73306abfe5787339b5dba7276ba")
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
                .addQuery("api_key=3fb4c73306abfe5787339b5dba7276ba")
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
                .addQuery("api_key=3fb4c73306abfe5787339b5dba7276ba")
                .build();

        Response response = httpRequestInstance.given().body(SessionBody).and().post(idUrl);
        requests = JsonHelper.responseToRequestsObj(response);
        reqToken = requests.getRequest_token();
    }

    public Requests CreateSession()
    {
        String SessionBody = "{\"request_token\":" + "\"" +  reqToken + "\"" + "}";

        URL idUrl = new URLBuilder()
                .addDomain(PropertiesHelper.getValueByKey("url.base"))
                .addPathStep("authentication/session/new")
                .addQuery("api_key=3fb4c73306abfe5787339b5dba7276ba")
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
                .addQuery("api_key=3fb4c73306abfe5787339b5dba7276ba")
                .build();

        Response response = httpRequestInstance.given().body(SessionBody).and().delete(idUrl);
        requests = JsonHelper.responseToRequestsObj(response);
        sessionID = requests.getSuccess();
        return sessionID;
    }


}












