package orchestra.instrument.identity;

public class IdentityProvider {

    private Identity identity;

    public IdentityProvider(Identity identity) {
        this.identity = identity;
    }

    public Identity get(){
        return identity;
    }
}
