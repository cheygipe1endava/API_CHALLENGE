package controllers;

import entities.List;
import helpers.JsonHelper;
import io.restassured.response.Response;
import helpers.PropertiesHelper;
import builders.URLBuilder;
import java.net.URL;

public class ListController extends ApiAuthenticationController {

    private String listBody, listID, sessionID;
    private List response;
    private Response movieResponse, sendRequest;
    private int addedMovieID = 330457;

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
                        .addPathStep("list/" + listID)
                        .addQuery("&language=en-US")
                        .build();
            case "movieItems":
                return new URLBuilder()
                        .addDomain(PropertiesHelper.getValueByKey("url.base"))
                        .addPathStep("list/" + listID + "/item_status")
                        .addQuery("&movie_id=" + addedMovieID)
                        .build();
            case "removeItems":
                return new URLBuilder()
                        .addDomain(PropertiesHelper.getValueByKey("url.base"))
                        .addPathStep("list/" + listID + "/remove_item")
                        .addQuery("&session_id=" + sessionID)
                        .build();
            case "clearList":
                return new URLBuilder()
                        .addDomain(PropertiesHelper.getValueByKey("url.base"))
                        .addPathStep("list/" + listID + "/clear")
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

    public void sendCreateList()
    {
        sendRequest = requestSpecification.given().body("{\"name\":\"Mock List Challenge Correction\"," +
                                            "\"description\":\"\"," + "\"language\":\"en\"}")
                                            .and().post(gettingListURL("listCreation"));
    }

    public List getResponseBody()
    {
        response = JsonHelper.responseToListObj(sendRequest);
        return response;
    }

    public void createMovieBody()
    {
        listBody = "{\"media_id\": " + addedMovieID + "}";
    }

    public void sendAddMovieRequest()
    {
        movieResponse = requestSpecification.given().body(listBody).and().post(gettingListURL("addMovie"));
    }

    public List getAddMovieResponse()
    {
        response = JsonHelper.responseToListObj(movieResponse);
        return response;
    }

    public void sendListDetailsRequest()
    {
        sendRequest = requestSpecification.get(gettingListURL("listDetails"));
    }

    public List getListDetailsResponse()
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

    public List getItemsInListResponse()
    {
        response = JsonHelper.responseToListObj(sendRequest);
        return response;
    }

    public void sendItemsRemovalRequest()
    {
        sendRequest = requestSpecification.given().body(listBody).and().post(gettingListURL("removeItems"));
    }

    public List getItemsRemovalResponse()
    {
        response = JsonHelper.responseToListObj(sendRequest);
        return response;
    }

    public void sendClearListRequest()
    {
        sendRequest = requestSpecification.post(gettingListURL("clearList"));
    }

    public List getClearListResponse()
    {
        response = JsonHelper.responseToListObj(sendRequest);
        return response;
    }

    public Response sendDeleteList()
    {
        sendRequest = requestSpecification.delete(gettingListURL("deleteList"));
        return sendRequest;
    }

    public List getDeleteList()
    {
        response = JsonHelper.responseToListObj(sendRequest);
        return response;
    }

    public List createList()
    {
        sendCreateList();
        this.listID = getResponseBody().getList_id();
        return getResponseBody();
    }

    public List addMovie()
    {
        createMovieBody();
        sendAddMovieRequest();
        return getAddMovieResponse();
    }

    public List deleteList()
    {
        sendDeleteList();
        return getDeleteList();
    }


}