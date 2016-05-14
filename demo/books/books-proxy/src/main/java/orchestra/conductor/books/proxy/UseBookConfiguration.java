package orchestra.conductor.books.proxy;

import orchestra.demo.books.api.BooksService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseBookConfiguration {

    @Bean
    public BooksService booksService(){
        return new BooksServiceProxy();
    }
}
