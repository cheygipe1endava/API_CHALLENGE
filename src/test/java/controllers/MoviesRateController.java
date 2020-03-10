package controllers;

import builders.URLBuilder;
import entities.ListRequests;
import entities.MovieRateRequests;
import helpers.JsonHelper;
import helpers.PropertiesHelper;
import io.restassured.response.Response;

import java.net.URL;

public class MoviesRateController extends ApiController{

    private String guestSessionID, sessionID;
    private int addedMovieID = 330457;
    private double rate = 8;
    private Response movieResponse, sendRequest;
    private MovieRateRequests response;

    public MoviesRateController(){  }

    public void getSessionID(String sessionID)
    {
        this.sessionID = sessionID;
    }

    public void getGuestSessionID(String guestSessionID)
    {
        this.guestSessionID = guestSessionID;
    }

    public URL gettingMovieRateURL(String endpoint){
        if (endpoint == "rateMovie")
        {
            return new URLBuilder()
                    .addDomain(PropertiesHelper.getValueByKey("url.base"))
                    .addPathStep("movie/" + addedMovieID + "/rating")
                    .addQuery("&guest_session_id=" + guestSessionID + "&session_id=" + sessionID)
                    .build();
        }
        return null;
    }

    public void sendRateMovie()
    {
        sendRequest = requestSpecification.given().body("{\"value\": " + rate + "}")
                                            .and().post(gettingMovieRateURL("rateMovie"));
    }

    public MovieRateRequests getRateMovie()
    {
        response = JsonHelper.responseToMovieRateObj(sendRequest);
        return response;
    }

    public void sendDeleteMovieRating()
    {
        sendRequest = requestSpecification.delete(gettingMovieRateURL("rateMovie"));
    }

    public MovieRateRequests getDeleteMovieRating()
    {
        response = JsonHelper.responseToMovieRateObj(sendRequest);
        return response;
    }
}
