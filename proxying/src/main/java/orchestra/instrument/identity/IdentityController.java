package orchestra.instrument.identity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/meta/identity")
public class IdentityController {

    @Autowired
    private IdentityProvider identityProvider;

    @RequestMapping(value = "", method = RequestMethod.GET)
    private Identity getIdentity() {
        return identityProvider.get();
    }
}
