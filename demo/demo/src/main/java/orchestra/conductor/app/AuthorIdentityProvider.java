package orchestra.conductor.app;

import orchestra.instrument.identity.ServiceIdentity;
import orchestra.instrument.identity.SimpleIdentityProvider;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class AuthorIdentityProvider extends SimpleIdentityProvider {

    @PostConstruct
    private void init(){
        identity = new ServiceIdentity()
                .service("authors")
                .version("0.0.1")
                .path(AuthorController.PATH)
                .host(getLocalHost())
                .randomId();
    }
}
