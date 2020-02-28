package controllers;


import entities.ListRequests;
import helpers.JsonHelper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import helpers.PropertiesHelper;
import builders.URLBuilder;

import java.net.URL;

public class ListController {

    String reqToken, sessionID, guestSession, deleteSession, listBody;
    private ListRequests requests, sessionRequest;

    private RequestSpecification httpRequestInstance = RestAssured.given().contentType(ContentType.JSON);

    public ListController(){   }

    public Response createList()
    {
        AuthenticationController cls = new AuthenticationController();
        String b = cls.Authenticate().getSession_id();

        listBody = "{\"name\":\"Mock List\"," +
            "\"description\":\"Mock list for tests in API challenge\"," +
                "\"language\":\"en\"}";

        URL idUrl = new URLBuilder()
                .addDomain(PropertiesHelper.getValueByKey("url.base"))
                .addPathStep("list")
                .addQuery(PropertiesHelper.getValueByKey("url.query") + "&session_id=" + b)
                .build();


        Response responser = httpRequestInstance.given().body(listBody).and().post(idUrl);
        String a = responser.getBody().asString();
        return responser;
        //requests = JsonHelper.responseToRequestsObj(responser);

    }



}
