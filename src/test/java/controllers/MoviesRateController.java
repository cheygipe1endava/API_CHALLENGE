package controllers;

import builders.URLBuilder;
import entities.MoviesRate;
import helpers.JsonHelper;
import helpers.PropertiesHelper;
import io.restassured.response.Response;

import java.net.URL;

public class MoviesRateController extends ApiRateController {

    private String guestSessionID, sessionID, addedMovieID;
    private Response sendRequest;
    private MoviesRate response;

    public MoviesRateController(){  }

    public void setMovieID(String movieID)
    {
        addedMovieID = movieID;
    }

    public void setSessionID(String sessionID)
    {
        this.sessionID = sessionID;
    }

    public void setGuestSessionID(String guestSessionID)
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
        sendRequest = requestSpecification.given().body(gettingBody("rate"))
                                            .and().post(gettingMovieRateURL("rateMovie"));
    }

    public MoviesRate getRateMovie()
    {
        response = JsonHelper.responseToMovieRateObj(sendRequest);
        return response;
    }

    public void sendDeleteMovieRating()
    {
        sendRequest = requestSpecification.delete(gettingMovieRateURL("rateMovie"));
    }

    public MoviesRate getDeleteMovieRating()
    {
        response = JsonHelper.responseToMovieRateObj(sendRequest);
        return response;
    }
}
