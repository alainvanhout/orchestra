package ochestra.proxying;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProxiesConfiguration {

    @Bean
    public RequestService requestService(){
        return new RequestServiceImpl();
    }

    @Bean
    public ResourceConversionService resourceConversionService(){
        return new ResourceConversionServiceImpl();
    }
}
