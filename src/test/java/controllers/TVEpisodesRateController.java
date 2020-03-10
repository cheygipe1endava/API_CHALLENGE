package controllers;

import builders.URLBuilder;
import entities.TVShowsRateRequest;
import helpers.JsonHelper;
import helpers.PropertiesHelper;
import io.restassured.response.Response;

import java.net.URL;

public class TVEpisodesRateController extends ApiController {

    private String guestSessionID, sessionID;
    private int addedTVShow = 39852;
    private double rate = 8;
    private Response sendRequest;
    private TVShowsRateRequest response;

    public TVEpisodesRateController(){  }

    public void setSessionID(String sessionID)
    {
        this.sessionID = sessionID;
    }

    public void setGuestSessionID(String guestSessionID)
    {
        this.guestSessionID = guestSessionID;
    }

    public URL gettingTVEpisodesRateURL(String endpoint){
        if (endpoint == "rateTVEpisode")
        {
            return new URLBuilder()
                    .addDomain(PropertiesHelper.getValueByKey("url.base"))
                    .addPathStep("tv/" + addedTVShow + "/season/" + 3 + "/episode/" + 1 + "/rating")
                    .addQuery("&guest_session_id=" + guestSessionID + "&session_id=" + sessionID)
                    .build();
        }
        return null;
    }

    public void sendRateTVEpisode()
    {
        sendRequest = requestSpecification.given().body("{\"value\": " + rate + "}")
                .and().post(gettingTVEpisodesRateURL("rateTVEpisode"));
    }

    public TVShowsRateRequest getRateTVEpisode()
    {
        response = JsonHelper.responseToTVShowRateObj(sendRequest);
        return response;
    }

    public void sendDeleteRateTVEpisode()
    {
        sendRequest = requestSpecification.delete(gettingTVEpisodesRateURL("rateTVEpisode"));
    }

    public TVShowsRateRequest getDeleteRateTVEpisode()
    {
        response = JsonHelper.responseToTVShowRateObj(sendRequest);
        return response;
    }

}
