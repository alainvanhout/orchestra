package orchestra.conductor.app;

import orchestra.instrument.identity.ServiceIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/meta/conductor")
public class ConductorController {

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
}
