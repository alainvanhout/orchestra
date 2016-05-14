package ochestra.proxying.request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonResponse {

    private String body;
    private GsonBuilder builder;
    private Gson gson;

    public JsonResponse(String body) {
        this.body = body;
        this.gson = new GsonBuilder().create();
    }

    public <T> T toObject(Class<T> clazz) {
        return (T) gson.fromJson(body, clazz);
    }

    public <T> List<T> toList(Class<T> clazz) {
        Type listType = new TypeToken<ArrayList<T>>(){}.getType();
        return (List<T>) gson.fromJson(body, listType);
    }
}
