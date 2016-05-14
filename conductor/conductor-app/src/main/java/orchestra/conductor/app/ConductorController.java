package orchestra.conductor.app;

import orchestra.instrument.identity.Identity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/meta/conductor")
public class ConductorController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    private String getInfo() {
        return "ok";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST, consumes = "application/json")
    private String register(@RequestBody Identity identity) {

        return "ok";
    }
}
