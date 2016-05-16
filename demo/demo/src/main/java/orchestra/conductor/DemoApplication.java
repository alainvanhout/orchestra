package orchestra.conductor;

import orchestra.conductor.books.proxy.UseBookService;
import orchestra.conductor.proxy.UseConductors;
import orchestra.instrument.ping.UsePing;
import orchestra.instrument.port.UseFreePort;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@UseBookService
@UseFreePort
@UseConductors
@UsePing
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
