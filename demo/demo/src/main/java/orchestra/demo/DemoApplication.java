package orchestra.demo;

import orchestra.demo.books.proxy.UseBookService;
import orchestra.instrument.port.UseFreePort;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@UseBookService
@UseFreePort
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
