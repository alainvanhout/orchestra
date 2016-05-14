package orchestra.demo.books.app;

import orchestra.instrument.identity.Identity;
import orchestra.instrument.identity.SimpleIdentityProvider;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class BooksIdentityProvider extends SimpleIdentityProvider {

    @PostConstruct
    public void init () {
        identity = new Identity()
                .service("books")
                .version("0.0.1")
                .randomId();
    }
}
