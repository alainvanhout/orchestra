package orchestra.conductor.books;

import ochestra.proxying.EnableProxies;
import orchestra.conductor.proxy.IsConducted;
import orchestra.instrument.Instrument;
import orchestra.instrument.identity.Identity;
import orchestra.instrument.identity.IdentityProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Instrument
@IsConducted
@EnableProxies
public class BookApplication {

    @Bean
    public IdentityProvider identityService() {
        return new IdentityProvider(new Identity()
                .service("books")
                .version("0.0.1")
                .randomId());
    }

    public static void main(String[] args) {
        SpringApplication.run(BookApplication.class, args);
    }
}
