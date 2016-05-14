package orchestra.conductor.proxy;

import ochestra.proxying.RequestService;
import orchestra.instrument.identity.ServiceIdentity;
import orchestra.instrument.identity.IdentityProvider;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ConductorService {

    @Autowired(required = false)
    private Collection<IdentityProvider> identityProviders;

    @Autowired
    private RequestService requestService;

    @Value("${orchestra.conductor.port.min:8080}")
    private int portMin;

    @Value("${orchestra.conductor.port.max:10000}")
    private int portMax;

    private List<ServiceIdentity> conductors = new ArrayList<>();

    @PostConstruct
    private void init() {
        register();
    }

    public void register() {
        if (identityProviders != null) {
            for (IdentityProvider identityProvider : identityProviders) {
                register(identityProvider.get());
            }
        }
    }

    private void register(ServiceIdentity self) {
        for (int port = portMin; port <= portMax; ++port) {
            if (inUse(port)) {
                String url = "http://localhost:" + port;
                ServiceIdentity identity = requestService.retrieve(url + "/meta/identity", "GET", ServiceIdentity.class);
                // only register to conductors (and not to myself)
                if (identity != null && isConductor(identity) && !self.equals(identity)) {
                    requestService.perform(url + "/meta/conductor/register", "POST", self);
                    conductors.add(identity);
                }
            }
        }
    }

    public ServiceIdentity requisition(ServiceIdentity example){
        if (conductors.size() > 0){
            ServiceIdentity conductor = conductors.get(0);
            String url = "http://" + conductor.getHost() + ":" + conductor.getPort();
            return requestService.retrieve(url + "/meta/conductor/requisition", "POST", example, ServiceIdentity.class);
        }
        return null;
    }

    private boolean isConductor(ServiceIdentity identity) {
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
