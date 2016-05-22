package ochestra.proxying.discovery;

import ochestra.proxying.request.RequestService;
import orchestra.instrument.identity.ServiceIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class DiscoveryServiceImpl implements DiscoveryService {

    @Autowired
    private RequestService requestService;

    private String hostAddress;

    @PostConstruct
    private void init(){
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            // log
        }
    }


    @Override
    public ServiceIdentity findOne(ServiceIdentity example, int minPort, int maxPort) {
        return formSearchStream(example, "http://localhost", minPort, maxPort)
                .findFirst().orElse(null);
    }

    @Override
    public List<ServiceIdentity> findAll(ServiceIdentity example, int minPort, int maxPort) {
        return formSearchStream(example, "http://localhost", minPort, maxPort)
                .collect(Collectors.toList());
    }

    private Stream<ServiceIdentity> formSearchStream(ServiceIdentity example, String address, int minPort, int maxPort) {
        return IntStream.rangeClosed(minPort, maxPort) // for all ports in the range
                .filter(this::portInUse) // only those ports that are in use
                .mapToObj(i ->  retrieveIdentityForPort(address, i)) // try to collect a service identity
                .filter(identity -> matches(identity, example)); // only those that matche the example service identity
    }

    private boolean matches(ServiceIdentity identity, ServiceIdentity example) {
        // this takes care of identity possibly being null
        if (identity == null || example == null) {
            return false;
        }
        if (example.getId() != null) {
            return example.equals(identity.getId());
        }
        return (example.getService() == null || example.getService().equals(identity.getService()))
                && (example.getVersion() == null || example.getVersion().equals(identity.getVersion()));
    }

    private boolean portInUse(int port) {
        try {
            ServerSocket socket = new ServerSocket(port);
            socket.close();
            return false;
        } catch (IOException ex) {
            return true;
        }
    }

    private ServiceIdentity retrieveIdentityForPort(String address, int port) {
        String url = address + ":" + port;
        return requestService.retrieve(url + "/meta/identity", "GET", ServiceIdentity.class);
    }
}
