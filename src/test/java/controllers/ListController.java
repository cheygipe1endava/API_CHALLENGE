package controllers;

import entities.List;
import helpers.JsonHelper;
import io.restassured.response.Response;
import helpers.PropertiesHelper;
import builders.URLBuilder;
import java.net.URL;

public class ListController extends ApiListController {

    private String listBody, listID, sessionID, addedMovieID;
    private List response;
    private Response movieResponse, sendRequest;
    private final String SESSION_ID = "session_id";
    private final String ADD_ITEM = "/add_item";
    private final String ITEM_STATUS = "/item_status";
    private final String MOVIE_ID = "movie_id";
    private final String REMOVE_ITEM = "/remove_item";
    private final String CLEAR = "/clear";
    private final String CONFIRM = "confirm";

    public ListController(String sessionID)
    {
        this.sessionID = sessionID;
        requestSpecification.basePath("list").queryParam(API_KEY, PropertiesHelper.getValueByKey("api.key"))
                                             .queryParam(SESSION_ID, this.sessionID);
    }

    public void setListID(String listIDReturn)
    {
        listID = listIDReturn;
    }

    public void setMovieID(String movieID)
    {
        addedMovieID = movieID;
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

    public List getResponseBody()
    {
        response = JsonHelper.responseToListObj(requestSpecification.given().body("{\"name\":\"Mock List Challenge Correction\"," +
                "\"description\":\"\"," + "\"language\":\"en\"}")
                .and().post());
        listID = response.getList_id();
        return response;
    }

    public String returnListID()
    {
        return listID;
    }

    public List getAddMovie()
    {
        return JsonHelper.responseToListObj(requestSpecification.given().body("{\"media_id\": " + addedMovieID + "}")
                .and().post("/" + listID + ADD_ITEM));
    }

    public List getListDetails()
    {
        return JsonHelper.responseToListObj(requestSpecification.get("/" + listID));
    }

    public List getItemsInListResponse()
    {
        return JsonHelper.responseToListObj(requestSpecification.queryParam(MOVIE_ID, addedMovieID).get("/" + listID + ITEM_STATUS));
    }

    public List getItemsRemoval()
    {
        return JsonHelper.responseToListObj(requestSpecification.given().body("{\"media_id\": " + addedMovieID + "}")
                .and().post("/" + listID + REMOVE_ITEM));
    }

    public List getListClear()
    {
        return JsonHelper.responseToListObj(requestSpecification.queryParam(CONFIRM, true).post("/" + listID + CLEAR));
    }

    public List getDeleteList()
    {
        return JsonHelper.responseToListObj(requestSpecification.delete("/" + listID));
    }

    public List createList()
    {
        return getResponseBody();
    }

    public List addMovie()
    {
        return getAddMovie();
    }

    public List deleteList()
    {
        return getDeleteList();
    }


}