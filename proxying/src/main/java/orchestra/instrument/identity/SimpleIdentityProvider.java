package orchestra.instrument.identity;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class SimpleIdentityProvider implements IdentityProvider {

    protected ServiceIdentity identity;

    @Override
    public ServiceIdentity get(){
        return identity;
    }

    protected static String getLocalHost() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return null;
        }
    }
}
