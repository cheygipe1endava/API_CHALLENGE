package controllers;

import builders.URLBuilder;
import entities.TVEpisodesRate;
import helpers.JsonHelper;
import helpers.PropertiesHelper;
import io.restassured.response.Response;

import java.net.URL;

public class TVEpisodesRateController extends ApiRateController {

    private String guestSessionID, sessionID, addedTVShow;
    private int season, episode;
    private double rate = 8;
    private Response sendRequest;
    private TVEpisodesRate response;

    public TVEpisodesRateController(){  }

    public void setTVShowID(String movieID)
    {
        addedTVShow = movieID;
    }

    public void setShowSeasonID(int seasonID)
    {
        season = seasonID;
    }

    public void setShowSeasonEpisodeID(int episodeID)
    {
        episode = episodeID;
    }

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
                    .addPathStep("tv/" + addedTVShow + "/season/" + season + "/episode/" + episode + "/rating")
                    .addQuery("&guest_session_id=" + guestSessionID + "&session_id=" + sessionID)
                    .build();
        }
        return null;
    }

    public void sendRateTVEpisode()
    {
        sendRequest = requestSpecification.given().body(gettingBody("rate"))
                .and().post(gettingTVEpisodesRateURL("rateTVEpisode"));
    }

    public TVEpisodesRate getRateTVEpisode()
    {
        response = JsonHelper.responseToTVEpisodesRateObj(sendRequest);
        return response;
    }

    public void sendDeleteRateTVEpisode()
    {
        sendRequest = requestSpecification.delete(gettingTVEpisodesRateURL("rateTVEpisode"));
    }

    public TVEpisodesRate getDeleteRateTVEpisode()
    {
        response = JsonHelper.responseToTVEpisodesRateObj(sendRequest);
        return response;
    }

}
