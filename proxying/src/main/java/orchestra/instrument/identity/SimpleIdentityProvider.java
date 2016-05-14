package orchestra.instrument.identity;

public class SimpleIdentityProvider implements IdentityProvider {

    protected Identity identity;

    @Override
    public Identity get(){
        return identity;
    }
}
