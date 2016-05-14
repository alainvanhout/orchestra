package ochestra.proxying.request;

import ochestra.proxying.ResourceConversionService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private ResourceConversionService resourceConversionService;

    public String perform(String url, String method){
        return perform(url, method, null);
    }

    @Override
    public JsonResponse perform(Endpoint endpoint, String body, String path, String method){
        HttpURLConnection connection = null;

        try {
            URL obj = new URL(endpoint.toUrl());
            connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod(method);
            if (body != null){
                connection.setDoOutput(true);
                connection.setDoInput(true);
                connection.setRequestProperty("Content-Type", "application/json");
                OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                writer.write(resourceConversionService.toJson(body));
                writer.flush();
            }
            connection.getResponseCode();
            return new JsonResponse(IOUtils.toString(connection.getInputStream()));
        } catch (IOException e) {
            return null;
            // throw exception?
        }
    }

    @Override
    public String perform(String url, String method, Object body){
        HttpURLConnection connection = null;

        try {
            URL obj = new URL(url);
            connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod(method);
            if (body != null){
                connection.setDoOutput(true);
                connection.setDoInput(true);
                connection.setRequestProperty("Content-Type", "application/json");
//                connection.setRequestProperty("Accept", "application/json");
                OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                writer.write(resourceConversionService.toJson(body));
                writer.flush();
            }
            connection.getResponseCode();
            return IOUtils.toString(connection.getInputStream());
        } catch (IOException e) {
            return null;
            // throw exception?
        }
    }

    @Override
    public <T> T retrieve(String url, String method, Class<T> clazz) {
        return resourceConversionService.toResource(perform(url, method), clazz);
    }

    @Override
    public <T> T retrieve(String url, String method, Object body, Class<T> clazz) {
        return resourceConversionService.toResource(perform(url, method, body), clazz);
    }
}
