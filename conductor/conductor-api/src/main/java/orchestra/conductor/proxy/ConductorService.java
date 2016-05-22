package orchestra.conductor.proxy;

import ochestra.proxying.discovery.DiscoveryService;
import ochestra.proxying.request.RequestService;
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

    @Autowired
    private DiscoveryService discoveryService;

    @Value("${orchestra.conductor.port.min:8080}")
    private int portMin;

    @Value("${orchestra.conductor.port.max:10000}")
    private int portMax;

    private List<ServiceIdentity> conductors = new ArrayList<>();

    @PostConstruct
    private void init() {
        discoverConductors();
        register();
    }

    public void register() {
        if (identityProviders != null) {
            // register each service identity that resides in this application instance
            for (IdentityProvider identityProvider : identityProviders) {
                register(identityProvider.get());
            }
        }
    }

    private void register(ServiceIdentity self) {
        discoverConductors();

        for (ServiceIdentity conductor : conductors) {
            String url = toUrl(conductor);
            requestService.perform(url + "/register", "POST", self);
        }
    }

    private void discoverConductors() {
        this.conductors.addAll(discoveryService.findAll(new ServiceIdentity().service("conductor"), portMin, portMax));
    }

    public ServiceIdentity requisition(ServiceIdentity example){
        if (conductors.size() == 0){
            discoverConductors();
        }
        if (conductors.size() > 0){
            ServiceIdentity conductor = conductors.get(0);
            String url = "http://" + conductor.getHost() + ":" + conductor.getPort();
            return requestService.retrieve(url + "/meta/conductor/requisition", "POST", example, ServiceIdentity.class);
        }
        return null;
    }

    public String requisitionUrl(String service){
        return requisitionUrl(service, null);
    }

    public String requisitionUrl(String service, String version){
        return requisitionUrl(new ServiceIdentity().service(service).version(version));
    }

    public String requisitionUrl(ServiceIdentity example){
        return toUrl(requisition(example));
    }

    private String toUrl(ServiceIdentity identity) {
        return "http://" + identity.getHost() + ":" + identity.getPort() + identity.getPath();
    }
}
