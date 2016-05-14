package ochestra.proxying;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Service;

@Service
public class ResourceConversionServiceImpl implements ResourceConversionService {

    @Override
    public <T> T toResource(String body, Class<T> clazz) {

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return (T)gson.fromJson(body, clazz);
    }

    @Override
    public String toJson(Object body) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.toJson(body);
    }

}
