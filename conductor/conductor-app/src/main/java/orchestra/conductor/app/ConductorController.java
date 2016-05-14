package orchestra.conductor.app;

import orchestra.instrument.identity.ServiceIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
