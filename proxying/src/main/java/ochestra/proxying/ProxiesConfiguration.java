package ochestra.proxying;

import ochestra.proxying.discovery.DiscoveryService;
import ochestra.proxying.discovery.DiscoveryServiceImpl;
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
    public DiscoveryService discoveryService(){
        return new DiscoveryServiceImpl();
    }

    @Bean
    public ResourceConversionService resourceConversionService(){
        return new ResourceConversionServiceImpl();
    }
}
