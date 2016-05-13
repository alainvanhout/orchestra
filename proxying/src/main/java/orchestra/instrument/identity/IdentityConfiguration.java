package orchestra.instrument.identity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IdentityConfiguration {

    @Bean
    public IdentityController identityController(){
        return new IdentityController();
    }
}
