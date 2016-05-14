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

public class ConductorService {

    @Autowired
    private IdentityProvider identityProvider;

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

        for (int port = portMin; port <= portMax; ++port) {
            if (inUse(port)) {
                Identity identity = requestService.retrieve("http://localhost:" + port + "/meta/identity", "GET", Identity.class);
                if (identity != null && StringUtils.equals(identity.getService(), "conductor")) {
                    identity = identity;
                }
            }
        }
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
