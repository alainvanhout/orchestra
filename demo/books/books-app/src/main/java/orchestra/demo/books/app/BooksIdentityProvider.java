package orchestra.demo.books.app;

import orchestra.instrument.identity.ServiceIdentity;
import orchestra.instrument.identity.SimpleIdentityProvider;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
public class BooksIdentityProvider extends SimpleIdentityProvider {

    @PostConstruct
    public void init () {
        identity = new ServiceIdentity()
                .service("books")
                .version("0.0.1")
                .path(BooksController.PATH)
                .host(getLocalHost())
                .randomId();
    }
}
