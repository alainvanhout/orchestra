package orchestra.conductor.app;

import ochestra.proxying.request.RequestService;
import orchestra.instrument.identity.ServiceIdentity;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class IdentityRegistrationService {

    @Autowired
    private RequestService requestService;

    @Value("${orchestra.port.min:8080}")
    private int globalPortMin;

    @Value("${orchestra.port.max:10000}")
    private int globalPortMax;

    private Set<ServiceIdentity> catalogue = ConcurrentHashMap.newKeySet();
    private List<String> jars;

    @PostConstruct
    private void init() {
        buildCatalogue();
        assembleJarList();
        startEssentialServices();
    }

    private void startEssentialServices() {

        String essentials = "demo";

        System.out.println("Starting essential services: " + essentials);

        String[] essentialServices = StringUtils.split(essentials, ",");
        for (String service : essentialServices) {
            // TODO: check if service is already in catalogue. If so, skip here

            String jar = getJarForService(service);
            if (jar != null) {
                runJar(jar);
            } else {
                System.out.println("Could not find jar for " + service);
            }
        }
        buildCatalogue();
    }

    private String getJarForService(String service) {
        return jars.stream()
                .filter(j -> new File(j).getName().startsWith(service))
                .findFirst().orElse(null);
    }

    private void runJar(String jar) {

        System.out.println("Running jar: " + jar);
        try {
            Process process = Runtime.getRuntime().exec("java -jar " + jar);

//            ProcessBuilder pb = new ProcessBuilder("java", "-jar", jar);
////            pb.directory(new File("preferred/working/directory"));
//            Process process = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String read = null;
            while ((read = reader.readLine()) != null) {
                System.out.println(read);
                if (read.contains("Started ") && read.contains("JVM running for")) {
                    break;
                }
            }
            System.out.println("Jar is running: " + jar);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void assembleJarList() {
        try {
            File root = new File("");
            jars = Files.walk(Paths.get(root.toURI()))
                    .map(Path::toFile)
                    .map(File::getAbsolutePath)
                    .filter(j -> j.endsWith("-SNAPSHOT.jar") && (j.contains("-app-") || j.contains("demo-")))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Assembled jar list: " + jars);
    }

    public void buildCatalogue() {
        System.out.println("Building catalogue");
        for (int port = globalPortMin; port <= globalPortMax; ++port) {
            if (inUse(port)) {
                ServiceIdentity identity = retrieveIdentityForPort(port);
                if (identity != null) {
                    register(identity);
                }
            }
        }
    }

    private ServiceIdentity retrieveIdentityForPort(int port) {
        String url = "http://localhost:" + port;
        return requestService.retrieve(url + "/meta/identity", "GET", ServiceIdentity.class);
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

    public void register(ServiceIdentity identity) {
        catalogue.add(identity);
        System.out.println("Registered service: " + ToStringBuilder.reflectionToString(identity, ToStringStyle.MULTI_LINE_STYLE));
    }

    public Collection<ServiceIdentity> getByExample(ServiceIdentity example) {
        // first just look it up in the catalogue
        List<ServiceIdentity> services = fromCatalogue(example);

        // if not present in the catalogue, first just try to rebuild the catalogue
        if (services.size() == 0){
            buildCatalogue();
            services = fromCatalogue(example);

        }

        // if still not present in catalogue, try to muster a new instance with a jar
        if (services.size() == 0) {
            System.out.println("No service of that name available. Attempting to muster one.");
            String jar = getJarForService(example.getService());
            if (jar != null) {
                runJar(jar);
                System.out.println("Service started. Rebuilding catalogue.");
                buildCatalogue();
                services = fromCatalogue(example);
                if (services.size() == 0) {
                    System.out.println("Service does not seem to have started.");
                }
            } else {
                System.out.println("No jar available for service");
            }
        }

        return services;
    }

    private List<ServiceIdentity> fromCatalogue(ServiceIdentity example) {
        return catalogue.parallelStream()
                .filter(i -> {
                    if (example.getId() != null) {
                        return example.equals(i);
                    }
                    return (example.getService() == null || example.getService().equals(i.getService()))
                            && (example.getVersion() == null || example.getVersion().equals(i.getVersion()));
                }).collect(Collectors.toList());
    }

    public ServiceIdentity getOneByExample(ServiceIdentity example) {
        return getByExample(example).stream().findFirst().orElse(null);
    }

    public Set<ServiceIdentity> getCatalogue() {
        return catalogue;
    }
}
