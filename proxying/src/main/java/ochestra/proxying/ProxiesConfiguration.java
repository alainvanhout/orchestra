package ochestra.proxying;

import ochestra.proxying.request.RequestService;
import ochestra.proxying.request.RequestServiceImpl;
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
