package ochestra.proxying;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private ResourceConversionService resourceConversionService;

    @Override
    public String retrieve(String url, String method){
        HttpURLConnection connection = null;

        try {
            URL obj = new URL(url);
            connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod(method);
            connection.getResponseCode();
            return IOUtils.toString(connection.getInputStream());
        } catch (IOException e) {
            return null;
            // throw exception?
        }
    }

    @Override
    public <T> T retrieve(String url, String method, Class<T> clazz) {
        return resourceConversionService.toResource(retrieve(url, method), clazz);
    }
}
