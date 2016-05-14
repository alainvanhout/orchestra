package orchestra.demo.books.app;

import orchestra.demo.books.api.Book;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(BooksController.PATH)
public class BooksController {

    public static final String PATH = "/books";

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    private Book getBook(){
        Book book = new Book();
        book.setTitle("My book");
        book.setAuthor("My author");
        return book;
    }
}
