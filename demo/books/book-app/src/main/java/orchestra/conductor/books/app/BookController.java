package orchestra.conductor.books.app;

import orchestra.conductor.books.api.Book;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    private Book getBook(){
        Book book = new Book();
        book.setTitle("My book");
        book.setAuthor("My author");
        return book;
    }
}
