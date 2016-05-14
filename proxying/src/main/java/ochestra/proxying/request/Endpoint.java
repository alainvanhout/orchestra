package ochestra.proxying.request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ochestra.proxying.ResourceConversionService;
import orchestra.instrument.identity.ServiceIdentity;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Endpoint {

    private ServiceIdentity identity;
    private RequestService requestService;

    public Endpoint(ServiceIdentity identity) {
        this.identity = identity;
    }

    public Endpoint requestService(RequestService requestService) {
        this.requestService = requestService;
        return this;
    }

    public String toUrl() {
        return "http://" + identity.getHost() + ":" + identity.getPort() + identity.getPath();
    }

    public JsonResponse send(String body, String path, String method){
        HttpURLConnection connection = null;

        try {
            URL obj = new URL(toUrl() + path);
            connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod(method);
            if (body != null){
                connection.setDoOutput(true);
                connection.setDoInput(true);
                connection.setRequestProperty("Content-Type", "application/json");
                OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                writer.write(toJson(body));
                writer.flush();
            }
            connection.getResponseCode();
            return new JsonResponse(IOUtils.toString(connection.getInputStream()));
        } catch (IOException e) {
            return null;
            // throw exception?
        }
    }

    public String toJson(Object body) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.toJson(body);
    }
}
