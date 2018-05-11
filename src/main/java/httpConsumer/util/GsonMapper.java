package httpConsumer.util;

import httpConsumer.ModelList;
import com.google.gson.Gson;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucas on 24/06/2016.
 */
@Default
public class GsonMapper {

    private Gson gson;

    @Inject
    public GsonMapper() {
        gson = new Gson();
    }

    public String toJson(Object object) {
        return gson.toJson(object);
    }

    public <T> List<T> convertJsonToList(String jsonString, Class<T> type) {
        try {
            return gson.fromJson(jsonString, new ModelList<>(type));
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<T>();
        }
    }

    public <T> T convertJson(String json, Class<T> type) {
        return gson.fromJson(json, type);
    }
}
