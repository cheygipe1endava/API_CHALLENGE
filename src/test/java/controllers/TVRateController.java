package controllers;

import builders.URLBuilder;
import entities.TVShowsRate;
import helpers.JsonHelper;
import helpers.PropertiesHelper;
import io.restassured.response.Response;

import java.net.URL;

public class TVRateController extends ApiRateController {

    private String guestSessionID, sessionID;
    private int addedTVShow = 39852;
    private double rate = 8;
    private Response sendRequest;
    private TVShowsRate response;

    public TVRateController(){  }

    public void getSessionID(String sessionID)
    {
        this.sessionID = sessionID;
    }

    public void getGuestSessionID(String guestSessionID)
    {
        this.guestSessionID = guestSessionID;
    }

    public URL gettingTVShowRateURL(String endpoint){
        if (endpoint == "rateTVShow")
        {
            return new URLBuilder()
                    .addDomain(PropertiesHelper.getValueByKey("url.base"))
                    .addPathStep("tv/" + addedTVShow + "/rating")
                    .addQuery("&guest_session_id=" + guestSessionID + "&session_id=" + sessionID)
                    .build();
        }
        return null;
    }

    public void sendRateTVShow()
    {
        sendRequest = requestSpecification.given().body(gettingBody("rate"))
                .and().post(gettingTVShowRateURL("rateTVShow"));
    }

    public TVShowsRate getRateTVShow()
    {
        response = JsonHelper.responseToTVShowRateObj(sendRequest);
        return response;
    }

    public void sendDeleteRateTVShow()
    {
        sendRequest = requestSpecification.delete(gettingTVShowRateURL("rateTVShow"));
    }

    public TVShowsRate getDeleteRateTVShow()
    {
        response = JsonHelper.responseToTVShowRateObj(sendRequest);
        return response;
    }
}
