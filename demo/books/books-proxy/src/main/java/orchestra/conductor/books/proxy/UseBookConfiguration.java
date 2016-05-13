package orchestra.conductor.books.proxy;

import orchestra.conductor.books.api.BookService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseBookConfiguration {

    @Bean
    public BookService bookService(){
        return new BookServiceProxy();
    }
}