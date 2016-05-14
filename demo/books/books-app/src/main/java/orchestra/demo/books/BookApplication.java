package orchestra.demo.books;

import ochestra.proxying.EnableProxies;
import orchestra.conductor.proxy.UseConductors;
import orchestra.instrument.Instrument;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Instrument
@UseConductors
@EnableProxies
public class BookApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookApplication.class, args);
    }
}
