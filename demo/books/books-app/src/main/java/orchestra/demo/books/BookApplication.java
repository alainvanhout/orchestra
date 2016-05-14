package orchestra.demo.books;

import ochestra.proxying.EnableProxies;
import orchestra.conductor.proxy.IsConducted;
import orchestra.instrument.Instrument;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Instrument
@IsConducted
@EnableProxies
public class BookApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookApplication.class, args);
    }
}
