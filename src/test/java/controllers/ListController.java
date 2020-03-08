package controllers;

import entities.ListRequests;
import helpers.JsonHelper;
import io.restassured.response.Response;
import helpers.PropertiesHelper;
import builders.URLBuilder;
import java.net.URL;

public class ListController extends ApiController{

    private String sessionID, listBody, successString, listID, statusCode;
    private ListRequests requests, response;
    private URL idUrl;
    private Response movieResponse, sendRequest;

    public ListController(){}

    public void getSessionID(String sessionID)
    {
        this.sessionID = sessionID;
    }

    public URL gettingListURL(String endpoint){
        switch (endpoint){
            case "listCreation":
                return new URLBuilder()
                        .addDomain(PropertiesHelper.getValueByKey("url.base"))
                        .addPathStep("list")
                        .addQuery("&session_id=" + sessionID)
                        .build();
            default:
        }
        return null;
    }

    public void createListBody()
    {
        listBody = "{\"name\":\"Mock List challenge\"," +
                "\"description\":\"Just an awesome list. Mock list for API test challenge.\"," +
                "\"language\":\"en\"}";
    }

    public void sendCreateListResponse()
    {
        sendRequest = requestSpecification.given().body(listBody).and().post(gettingListURL("listCreation"));
    }

    public ListRequests getResponseBody()
    {
        response = JsonHelper.responseToListObj(sendRequest);
        //successString = requests.getSuccess();
        //successString = JsonHelper.responseToListObj(sendRequest).getSuccess();
        return response;
    }

    public void getIDForList()
    {
        response = JsonHelper.responseToListObj(sendRequest);
        listID = response.getList_id();
    }

    public String  addMovie()
    {

        listBody = "{\"media_id\": 12}";

        idUrl = new URLBuilder()
                .addDomain(PropertiesHelper.getValueByKey("url.base"))
                .addPathStep("list/" + listID + "/add_item")
                .addQuery(PropertiesHelper.getValueByKey("url.query") + "&session_id=" + sessionID)
                .build();

        movieResponse = requestSpecification.given().body(listBody).and().post(idUrl);
        response = JsonHelper.responseToListObj(movieResponse);
        String statusCodeString = response.getStatus_code();
        return statusCodeString;
    }


    public Response getListDetails()
    {
        URL idUrl = new URLBuilder()
                .addDomain(PropertiesHelper.getValueByKey("url.base"))
                .addPathStep("list/" + listID)
                .addQuery(PropertiesHelper.getValueByKey("url.query") + "&language=en-US")
                .build();



        Response response = requestSpecification.get(idUrl);
        String listDetails = response.getBody().asString();
        return response;
    }

    public Response itemInList()
    {

        URL idUrl = new URLBuilder()
                .addDomain(PropertiesHelper.getValueByKey("url.base"))
                .addPathStep("list/" + listID + "/item_status")
                .addQuery(PropertiesHelper.getValueByKey("url.query") + "&movie_id=" + 18)
                .build();

        Response response = requestSpecification.get(idUrl);
        String items = response.getBody().asString();
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


        Response response = requestSpecification.given().body(listBody).and().post(idUrl);
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

        Response response = requestSpecification.post(idUrl);
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

        Response response = requestSpecification.delete(idUrl);
        String a = response.getBody().asString();
        requests = JsonHelper.responseToListObj(response);
        statusCode = requests.getStatus_code();//12
        return  response;
    }





}