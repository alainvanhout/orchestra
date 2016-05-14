package ochestra.proxying;

public interface RequestService {

    String perform(String url, String method);

    String perform(String url, String method, Object body);

    <T> T retrieve(String url, String method, Class<T> clazz);
}
