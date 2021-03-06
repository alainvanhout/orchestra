package orchestra.conductor.app;

import orchestra.instrument.identity.ServiceIdentity;
import orchestra.instrument.identity.SimpleIdentityProvider;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ConductorIdentityProvider extends SimpleIdentityProvider {

    @PostConstruct
    private void init(){
        identity = new ServiceIdentity()
                .service("conductor")
                .version("0.0.1")
                .path(ConductorController.PATH)
                .host(getLocalHost())
                .randomId();
    }
}
