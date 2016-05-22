package ochestra.proxying.discovery;

import orchestra.instrument.identity.ServiceIdentity;

import java.util.List;

public interface DiscoveryService {

    ServiceIdentity findOne(ServiceIdentity identity, int minPort, int maxPort);

    List<ServiceIdentity> findAll(ServiceIdentity identity, int minPort, int maxPort);
}
