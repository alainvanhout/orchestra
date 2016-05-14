package orchestra.conductor.app;

import orchestra.instrument.identity.ServiceIdentity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class IdentityRegistrationService {

    private Set<ServiceIdentity> identities = ConcurrentHashMap.newKeySet();

    public void register(ServiceIdentity identity) {
        identities.add(identity);
    }

    public Collection<ServiceIdentity> getByExample(ServiceIdentity example) {
        return identities.parallelStream()
                .filter(i -> {
                    if (example.getId() != null) {
                        return example.equals(i);
                    }
                    return (example.getService() == null || example.getService().equals(i.getService()))
                            && (example.getVersion() == null || example.getVersion().equals(i.getVersion()));
                }).collect(Collectors.toList());
    }
}
