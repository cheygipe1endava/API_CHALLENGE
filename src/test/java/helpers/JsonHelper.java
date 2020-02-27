package helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Requests;
import io.restassured.response.Response;

public class JsonHelper {

    protected static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

    public static String objectToJson(Object object) {
        return gson.toJson(object, object.getClass());
    }

    public static Requests responseToRequestsObj(Response response) {
        return gson.fromJson(response.body().asString(), Requests.class);
    }
}


