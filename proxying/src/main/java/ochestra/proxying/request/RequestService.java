package ochestra.proxying.request;

public interface RequestService {

    String perform(String url, String method);

    JsonResponse perform(Endpoint endpoint, String body, String path, String method);

    String perform(String url, String method, Object body);

    <T> T retrieve(String url, String method, Class<T> clazz);

    <T> T retrieve(String url, String method, Object body, Class<T> clazz);
}
