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
        return JsonHelper.responseToRequestsObj(requestSpecification.get(GUEST_SESSION + NEW));
    }

    public void getRequestToken()
    {
        requestToken = JsonHelper.responseToRequestsObj(requestSpecification.get(TOKEN + NEW)).getRequest_token();
        setRequestToken(requestToken);
    }

    public void getSessionWithLogin()
    {
        JsonHelper.responseToRequestsObj(requestSpecification.given().body(gettingBodies("sessionWithLogin"))
                .and().post(TOKEN + VALIDATE_WITH_LOGIN));
    }

    public Session getCreateSession()
    {
        getRequestBody = JsonHelper.responseToRequestsObj(requestSpecification.given().body(gettingBodies("createSession"))
                            .and().post(SESSION + "/" + NEW));
        setSessionID(getRequestBody.getSession_id());
        return getRequestBody;
    }

    public Session getDeleteSession()
    {
        return JsonHelper.responseToRequestsObj(requestSpecification.given().body(gettingBodies("deleteSession"))
                                                .and().delete(SESSION));
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
        getSessionWithLogin();
        getCreateSession();
        return getRequestBody;
    }

    public Session deleteSession()
    {
        getDeleteSession();
        return getRequestBody;
    }
}












