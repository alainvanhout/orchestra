package orchestra.demo.books.proxy;

import orchestra.demo.books.api.BookService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseBookConfiguration {

    @Bean
    public BookService bookService(){
        return new BookServiceProxy();
    }
}
