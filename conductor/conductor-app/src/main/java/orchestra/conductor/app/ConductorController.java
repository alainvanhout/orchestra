package orchestra.conductor.app;

import orchestra.instrument.identity.ServiceIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.tags.Param;

import java.util.Set;

@RestController
@RequestMapping(ConductorController.PATH)
public class ConductorController {

    public static final String PATH = "/meta/conductor";
    @Autowired
    private IdentityRegistrationService identityRegistrationService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    private String getInfo() {
        return "ok";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST, consumes = "application/json")
    private void register(@RequestBody ServiceIdentity identity) {
        identityRegistrationService.register(identity);
    }

    @RequestMapping(value = "requisition", method = RequestMethod.POST, consumes = "application/json")
    private ServiceIdentity requisition(@RequestBody ServiceIdentity identity) {
        ServiceIdentity oneByExample = identityRegistrationService.getOneByExample(identity);
        return oneByExample;
    }

    @RequestMapping(value = "catalogue", method = RequestMethod.GET, produces = "application/json")
    private Set<ServiceIdentity> catalogueJson(@RequestParam(defaultValue = "false") boolean build) {
        if (build){
            identityRegistrationService.buildCatalogue();
        }
        return identityRegistrationService.getCatalogue();
    }
}
