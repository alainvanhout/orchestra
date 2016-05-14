package orchestra.instrument.identity;

public class SimpleIdentityProvider implements IdentityProvider {

    protected ServiceIdentity identity;

    @Override
    public ServiceIdentity get(){
        return identity;
    }
}
