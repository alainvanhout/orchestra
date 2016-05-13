package orchestra.instrument.ping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PingConfiguration {

    @Bean
    public PingController pingController() {
        return new PingController();
    }
}
