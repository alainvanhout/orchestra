package orchestra.conductor.app;

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
}
