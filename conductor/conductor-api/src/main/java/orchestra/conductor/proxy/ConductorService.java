package orchestra.conductor.proxy;

import ochestra.proxying.RequestService;
import orchestra.instrument.identity.Identity;
import orchestra.instrument.identity.IdentityProvider;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Collection;

public class ConductorService {

    @Autowired
    private Collection<IdentityProvider> identityProviders;

    @Autowired
    private RequestService requestService;

    @Value("${orchestra.conductor.port.min:8080}")
    private int portMin;

    @Value("${orchestra.conductor.port.max:10000}")
    private int portMax;

    @PostConstruct
    private void init() {
        register();
    }

    public void register() {
        for (IdentityProvider identityProvider : identityProviders) {
            register(identityProvider.get());
        }
    }

    private void register(Identity self) {
        for (int port = portMin; port <= portMax; ++port) {
            if (inUse(port)) {
                String url = "http://localhost:" + port;
                Identity identity = requestService.retrieve(url + "/meta/identity", "GET", Identity.class);
                // only register to conductors (and not to myself)
                if (identity != null && isConductor(identity) && !StringUtils.equals(identity.getId(), self.getId()) ) {
                    requestService.perform(url + "/meta/conductor/register", "POST", self);
                }
            }
        }
    }

    private boolean isConductor(Identity identity) {
        return StringUtils.equals(identity.getService(), "conductor");
    }

    private boolean inUse(int port) {
        try {
            ServerSocket socket = new ServerSocket(port);
            socket.close();
            return false;
        } catch (IOException ex) {
            return true;
        }
    }
}
