package orchestra.instrument.port;

import orchestra.instrument.identity.IdentityProvider;
import orchestra.instrument.identity.SimpleIdentityProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Collection;

@Configuration
public class FreePortConfiguration {

    @Value("${orchestra.instrument.port.min:8080}")
    private int portMin;

    @Value("${orchestra.instrument.port.max:10000}")
    private int portMax;

    @Autowired(required = false)
    private Collection<IdentityProvider> identityProviders;

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        Integer port = getFreePort(portMin, portMax);

        if (port == null) {
            throw new IllegalStateException("Could not find a free port");
        }

        if (identityProviders != null) {
            for (IdentityProvider identityProvider : identityProviders) {
                identityProvider.get().port(port);
            }
        }

        return (container -> container.setPort(port));
    }

    private Integer getFreePort(int min, int max) {
        for (int port = min; port <= max; ++port) {
            try {
                ServerSocket socket = new ServerSocket(port);
                socket.close();
                return port;
            } catch (IOException ex) {
                // move to next port
            }
        }
        return null;
    }
}
