package orchestra.conductor.app;

import orchestra.instrument.identity.Identity;
import orchestra.instrument.identity.SimpleIdentityProvider;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ConductorIdentityProvider extends SimpleIdentityProvider {

    @PostConstruct
    private void init(){
        identity = new Identity()
                .service("conductor")
                .version("0.0.1")
                .randomId();
    }
}
