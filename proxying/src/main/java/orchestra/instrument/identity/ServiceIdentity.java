package orchestra.instrument.identity;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

public class ServiceIdentity {
    private String id;
    private String service;
    private String version;
    private Integer port;
    private String path;
    private String host;

    public String getId() {
        return this.id;
    }

    public ServiceIdentity id(String id) {
        this.id = id;
        return this;
    }

    public ServiceIdentity randomId() {
        return id(UUID.randomUUID().toString());
    }

    public String getService() {
        return service;
    }

    public ServiceIdentity service(String service) {
        this.service = service;
        return this;
    }

    public String getVersion() {
        return this.version;
    }

    public ServiceIdentity version(String version) {
        this.version = version;
        return this;
    }

    public Integer getPort() {
        return port;
    }

    public ServiceIdentity port(Integer port) {
        this.port = port;
        return this;
    }

    public String getPath() {
        return path;
    }

    public ServiceIdentity path(String path) {
        this.path = path;
        return this;
    }

    public String getHost() {
        return host;
    }

    public ServiceIdentity host(String host) {
        this.host = host;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ServiceIdentity) {
            return StringUtils.equals(id,((ServiceIdentity)obj).getId());
        }
        return false;
    }

    @Override
    public int hashCode() {
        if (id != null){
            return id.hashCode();
        }
        return 0;
    }
}
