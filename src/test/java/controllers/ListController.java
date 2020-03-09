package controllers;

import entities.ListRequests;
import entities.SessionRequests;
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
    private int temporaryListID = 135164;

    public ListController(){}

    public void getSessionID(String sessionID)
    {
        this.sessionID = sessionID;
    }

    public void getListID(String listIDReturn)
    {
        listID = listIDReturn;
    }


    public URL gettingListURL(String endpoint){
        switch (endpoint){
            case "listCreation":
                return new URLBuilder()
                        .addDomain(PropertiesHelper.getValueByKey("url.base"))
                        .addPathStep("list")
                        .addQuery("&session_id=" + sessionID)
                        .build();
            case "addMovie":
                return new URLBuilder()
                        .addDomain(PropertiesHelper.getValueByKey("url.base"))
                        .addPathStep("list/" + listID + "/add_item")
                        .addQuery("&session_id=" + sessionID)
                        .build();
            case "listDetails":
                return new URLBuilder()
                        .addDomain(PropertiesHelper.getValueByKey("url.base"))
                        .addPathStep("list/" + temporaryListID)
                        .addQuery("&language=en-US")
                        .build();
            case "movieItems":
                return new URLBuilder()
                        .addDomain(PropertiesHelper.getValueByKey("url.base"))
                        .addPathStep("list/" + temporaryListID + "/item_status")
                        .addQuery("&movie_id=" + 330457)
                        .build();
            case "removeItems":
                return new URLBuilder()
                        .addDomain(PropertiesHelper.getValueByKey("url.base"))
                        .addPathStep("list/" + temporaryListID + "/remove_item")
                        .addQuery("&session_id=" + sessionID)
                        .build();
            case "clearList":
                return new URLBuilder()
                        .addDomain(PropertiesHelper.getValueByKey("url.base"))
                        .addPathStep("list/" + temporaryListID + "/clear")
                        .addQuery("&session_id=" + sessionID + "&confirm=true")
                        .build();
            case "deleteList":
                return new URLBuilder()
                        .addDomain(PropertiesHelper.getValueByKey("url.base"))
                        .addPathStep("list/" + listID)
                        .addQuery("&session_id=" + sessionID)
                        .build();
            default:
        }
        return null;
    }



    public void createListBody()
    {
        listBody = "{\"name\":\"Mock List 1\"," +
                "\"description\":\"\"," +
                "\"language\":\"en\"}";
    }

    public void sendCreateListResponse()
    {
        sendRequest = requestSpecification.given().body(listBody).and().post(gettingListURL("listCreation"));
        String verifyListCreation = sendRequest.getBody().asString();
    }

    public ListRequests getResponseBody()
    {
        response = JsonHelper.responseToListObj(sendRequest);
        return response;
    }

    public void createMovieBody()
    {
        listBody = "{\"media_id\": 330457}";
    }

    public void sendAddMovieRequest()
    {
        movieResponse = requestSpecification.given().body(listBody).and().post(gettingListURL("addMovie"));
    }

    public ListRequests getAddMovieResponse()
    {
        //String verifySuccessAdd = movieResponse.getBody().asString();
        response = JsonHelper.responseToListObj(movieResponse);
        return response;
    }

    public void sendListDetailsRequest()
    {
        sendRequest = requestSpecification.get(gettingListURL("listDetails"));
    }

    public ListRequests getListDetailsResponse()
    {
        response = JsonHelper.responseToListObj(sendRequest);
        return response;
    }

    public String getListDetailsBody()
    {
        return sendRequest.getBody().asString();
    }

    public void sendItemsInListRequest()
    {
        sendRequest = requestSpecification.get(gettingListURL("movieItems"));
    }

    public ListRequests getItemsInListResponse()
    {
        response = JsonHelper.responseToListObj(sendRequest);
        return response;
    }

    public void sendItemsRemovalRequest()
    {
        sendRequest = requestSpecification.given().body(listBody).and().post(gettingListURL("removeItems"));
    }

    public ListRequests getItemsRemovalResponse()
    {
        response = JsonHelper.responseToListObj(sendRequest);
        return response;
    }

    public void sendClearListRequest()
    {
        sendRequest = requestSpecification.post(gettingListURL("clearList"));
    }

    public ListRequests getClearListResponse()
    {
        response = JsonHelper.responseToListObj(sendRequest);
        return response;
    }

    public Response sendDeleteList()
    {
        sendRequest = requestSpecification.delete(gettingListURL("deleteList"));
        return sendRequest;
    }

    public ListRequests getDeleteList()
    {
        String a = sendRequest.getBody().asString();
        response = JsonHelper.responseToListObj(sendRequest);
        return response;
    }

    public ListRequests createList()
    {
        createListBody();
        sendCreateListResponse();
        getResponseBody();
        this.listID = getResponseBody().getList_id();
        return getResponseBody();
    }

    public ListRequests deleteList()
    {
        sendDeleteList();
        getDeleteList();
        return getDeleteList();
    }
}