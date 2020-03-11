package controllers;

import entities.List;
import helpers.JsonHelper;
import helpers.PropertiesHelper;

public class ListController extends ApiListController {

    private String listID, sessionID, addedMovieID;
    private List response;
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
        setMovieIDController(addedMovieID);
    }

    public List getCreateList()
    {
        response = JsonHelper.responseToListObj(requestSpecification.given().body(gettingBodies("listCreation")).and().post());
        listID = response.getList_id();
        return response;
    }

    public String returnListID()
    {
        return listID;
    }

    public List getAddMovie()
    {
        return JsonHelper.responseToListObj(requestSpecification.given().body(gettingBodies("addedMovie"))
                .and().post("/" + listID + ADD_ITEM));
    }

    public List getListDetails()
    {
        return JsonHelper.responseToListObj(requestSpecification.get("/" + listID));
    }

    public List getItemsInList()
    {
        return JsonHelper.responseToListObj(requestSpecification.queryParam(MOVIE_ID, addedMovieID).get("/" + listID + ITEM_STATUS));
    }

    public List getItemsRemoval()
    {
        return JsonHelper.responseToListObj(requestSpecification.given().body(gettingBodies("addedMovie"))
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
}