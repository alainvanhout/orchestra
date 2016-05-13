package orchestra.instrument.port;

import ochestra.proxying.RequestService;
import ochestra.proxying.RequestServiceImpl;
import ochestra.proxying.ResourceConversionService;
import ochestra.proxying.ResourceConversionServiceImpl;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.ServerSocket;

@Configuration
public class FreePortConfiguration {

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        Integer port = getFreePort(8080, 10000);

        if (port == null){
            throw new IllegalStateException("Could not find a free port");
        }

        return (container -> {
            container.setPort(port);
        });
    }

    private Integer getFreePort(int lowerbound, int upperbound) {
        for (int port = lowerbound; port < upperbound; ++port) {
            try {
                ServerSocket serverSocket = new ServerSocket(port);
                serverSocket.close();
                return port;
            } catch (IOException ex) {
                continue; // try next port
            }
        }
        return null;
    }
}
