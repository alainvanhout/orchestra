package ochestra.proxying;

public interface ResourceConversionService {

    <T> T toResource(String body, Class<T> clazz);

    String toJson(Object body);
}
