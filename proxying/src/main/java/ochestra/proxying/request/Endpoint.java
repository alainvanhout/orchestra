package ochestra.proxying.request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import orchestra.instrument.identity.ServiceIdentity;

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
        return requestService.perform(this, body, path, method);
    }

    public String toJson(Object body) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.toJson(body);
    }
}
