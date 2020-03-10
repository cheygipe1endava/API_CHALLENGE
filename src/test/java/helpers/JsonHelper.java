package helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.*;
import io.restassured.response.Response;

public class JsonHelper {

    protected static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

    public static String objectToJson(Object object) {
        return gson.toJson(object, object.getClass());
    }

    public static Session responseToRequestsObj(Response response) {
        return gson.fromJson(response.body().asString(), Session.class);
    }

    public static List responseToListObj(Response response) {
        return gson.fromJson(response.body().asString(), List.class);
    }

    public static MoviesRate responseToMovieRateObj(Response response) {
        return gson.fromJson(response.body().asString(), MoviesRate.class);
    }

    public static TVShowsRate responseToTVShowRateObj(Response response) {
        return gson.fromJson(response.body().asString(), TVShowsRate.class);
    }

    public static TVEpisodesRate responseToTVEpisodesRateObj(Response response) {
        return gson.fromJson(response.body().asString(), TVEpisodesRate.class);
    }
}


