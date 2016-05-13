package orchestra.conductor;

import orchestra.instrument.identity.HasIdentity;
import orchestra.instrument.identity.Identity;
import orchestra.instrument.identity.IdentityProvider;
import orchestra.instrument.port.UseFreePort;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@UseFreePort
@HasIdentity
public class ConductorApplication {

    @Bean
    public IdentityProvider identityService() {
        return new IdentityProvider(new Identity()
                .service("conductor")
                .version("0.0.1")
                .randomId());
    }

    public static void main(String[] args) {
        SpringApplication.run(ConductorApplication.class, args);
    }
}
