package orchestra.instrument.identity;

import java.util.UUID;

public class Identity {
    private String id;
    private String service;
    private String version;

    public String getId() {
        return this.id;
    }

    public Identity id(String id) {
        this.id = id;
        return this;
    }

    public Identity randomId() {
        return id(UUID.randomUUID().toString());
    }

    public String getService() {
        return service;
    }

    public Identity service(String service) {
        this.service = service;
        return this;
    }

    public String getVersion() {
        return this.version;
    }

    public Identity version(String version) {
        this.version = version;
        return this;
    }
}
