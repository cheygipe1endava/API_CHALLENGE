package controllers;

import entities.Session;
import helpers.JsonHelper;
import io.restassured.response.Response;
import helpers.PropertiesHelper;

public class AuthenticationController extends ApiAuthenticationController {

    private String requestToken, sessionID;
    private Session getRequestBody;
    private Response response;
    private final String SESSION = "session";
    private final String NEW = "new";
    private final String TOKEN = "token/";
    private final String GUEST_SESSION = "/guest_session/";
    private final String VALIDATE_WITH_LOGIN = "validate_with_login";


    public AuthenticationController()
    {
        super();
        requestSpecification.basePath("authentication/").queryParam(API_KEY, PropertiesHelper.getValueByKey("api.key"));
    }

    public Session getGuestSession()
    {
        getRequestBody = JsonHelper.responseToRequestsObj(requestSpecification.get(GUEST_SESSION + NEW));
        return getRequestBody;
    }

    public void getRequestToken()
    {
        getRequestBody = JsonHelper.responseToRequestsObj(requestSpecification.get(TOKEN + NEW));
        requestToken = getRequestBody.getRequest_token();
    }

    public void sessionWithLogin()
    {
        getRequestBody = JsonHelper.responseToRequestsObj(requestSpecification.given().body("{\"username\":\"FELIPE_GIRALDO_PEREZ\","
                + "\"password\":\"1234\"," + "\"request_token\":" + "\"" +  requestToken + "\"" + "}")
                .and().post(TOKEN + VALIDATE_WITH_LOGIN));
        this.requestToken = getRequestBody.getRequest_token();
    }

    public Session createSession()
    {
        getRequestBody = JsonHelper.responseToRequestsObj(requestSpecification.given().body("{\"request_token\":" + "\"" +
                requestToken + "\"" + "}").and().post(SESSION + "/" + NEW));
        sessionID = getRequestBody.getSession_id();
        return getRequestBody;
    }

    public Session deleteSessionRequest()
    {
        getRequestBody = JsonHelper.responseToRequestsObj(requestSpecification.given().body("{\"session_id\":" + "\"" +
                sessionID + "\"" + "}").and().delete(SESSION));
        return getRequestBody;
    }

    public void getSessionID(String sessionID)
    {
        this.sessionID = sessionID;
    }

    public Session guestSession()
    {
        getGuestSession();
        return getRequestBody;
    }

    public Session Authenticate() {
        getRequestToken();
        sessionWithLogin();
        createSession();
        return getRequestBody;
    }

    public Session deleteSession()
    {
        deleteSessionRequest();
        return getRequestBody;
    }
}












