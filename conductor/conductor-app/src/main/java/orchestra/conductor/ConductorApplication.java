package orchestra.conductor;

import ochestra.proxying.EnableProxies;
import orchestra.instrument.identity.HasIdentity;
import orchestra.instrument.port.UseFreePort;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@UseFreePort
@HasIdentity
@EnableProxies
public class ConductorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConductorApplication.class, args);
    }
}
