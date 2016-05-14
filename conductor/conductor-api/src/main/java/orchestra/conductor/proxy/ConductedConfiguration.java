package orchestra.conductor.proxy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConductedConfiguration {

    @Bean
    public ConductorService conductorService() {
        return new ConductorService();
    }
}
