package controllers;


import entities.ListRequests;
import helpers.JsonHelper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import helpers.PropertiesHelper;
import builders.URLBuilder;
import netscape.javascript.JSException;
import netscape.javascript.JSObject;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.net.URL;

public class ListController {

    String listID, sessionID, statusCode, deleteSession, listBody;
    private ListRequests requests, sessionRequest;

    private RequestSpecification httpRequestInstance = RestAssured.given().contentType(ContentType.JSON);

    public ListController(){   }

    public void ListExr()
    {
        AuthenticationController cls = new AuthenticationController();
        sessionID = cls.Authenticate().getSession_id();
    }

    public Response createList()
    {


        listBody = "{\"name\":\"Mock List1\"," +
                "\"description\":\"Just an awesome list dawg. Mock list for API test. efwcfqcqe\"," +
                "\"language\":\"en\"}";


        URL idUrl = new URLBuilder()
                .addDomain(PropertiesHelper.getValueByKey("url.base"))
                .addPathStep("list")
                .addQuery(PropertiesHelper.getValueByKey("url.query") + "&session_id=" + sessionID)
                .build();


        Response response = httpRequestInstance.given().body(listBody).and().post(idUrl);
        requests = JsonHelper.responseToListObj(response);
        String a = response.getBody().asString();
        listID = requests.getList_id();
        return response;
    }

    public Response getListDetails()
    {
        URL idUrl = new URLBuilder()
                .addDomain(PropertiesHelper.getValueByKey("url.base"))
                .addPathStep("list/" + 134219)
                .addQuery(PropertiesHelper.getValueByKey("url.query") + "&language=en-US")
                .build();



        Response response = httpRequestInstance.get(idUrl);
        String a = response.getBody().asString();
        return response;
    }

    public Response itemInList()
    {

        URL idUrl = new URLBuilder()
                .addDomain(PropertiesHelper.getValueByKey("url.base"))
                .addPathStep("list/" + 134219 + "/item_status")
                .addQuery(PropertiesHelper.getValueByKey("url.query") + "&movie_id=" + 18)
                .build();

        Response response = httpRequestInstance.get(idUrl);
        String a = response.getBody().asString();
        return response;

    }

    public Response addMovie()
    {

        listBody = "{\"media_id\": 12}";

        URL idUrl = new URLBuilder()
                .addDomain(PropertiesHelper.getValueByKey("url.base"))
                .addPathStep("list/" + listID + "/add_item")
                .addQuery(PropertiesHelper.getValueByKey("url.query") + "&session_id=" + sessionID)
                .build();


        Response response = httpRequestInstance.given().body(listBody).and().post(idUrl);
        requests = JsonHelper.responseToListObj(response);
        statusCode = requests.getStatus_code();//12
        return response;
    }

    public Response removeMovie()
    {
        listBody = "{\"media_id\": 12}";

        URL idUrl = new URLBuilder()
                .addDomain(PropertiesHelper.getValueByKey("url.base"))
                .addPathStep("list/" + listID + "/remove_item")
                .addQuery(PropertiesHelper.getValueByKey("url.query") + "&session_id=" + sessionID)
                .build();


        Response response = httpRequestInstance.given().body(listBody).and().post(idUrl);
        requests = JsonHelper.responseToListObj(response);
        statusCode = requests.getStatus_code();//13
        return response;

    }

    public Response clearList()
    {
        URL idUrl = new URLBuilder()
                .addDomain(PropertiesHelper.getValueByKey("url.base"))
                .addPathStep("list/" + listID + "/clear")
                .addQuery(PropertiesHelper.getValueByKey("url.query") + "&session_id=" + sessionID + "&confirm=true")
                .build();

        Response response = httpRequestInstance.post(idUrl);
        requests = JsonHelper.responseToListObj(response);
        statusCode = requests.getStatus_code();//12
        return  response;

    }

    public Response deleteList()
    {
        URL idUrl = new URLBuilder()
                .addDomain(PropertiesHelper.getValueByKey("url.base"))
                .addPathStep("list/" + listID)
                .addQuery(PropertiesHelper.getValueByKey("url.query") + "&session_id=" + sessionID)
                .build();

        Response response = httpRequestInstance.delete(idUrl);
        String a = response.getBody().asString();
        //requests = JsonHelper.responseToListObj(response);
        //statusCode = requests.getStatus_code();//12
        return  response;
    }





}