package ochestra.proxying;

public interface RequestService {

    String retrieve(String url, String method);

    <T> T retrieve(String url, String method, Class<T> clazz);
}
