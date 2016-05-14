package orchestra.demo.books.app;

import orchestra.demo.books.api.Book;
import orchestra.demo.books.api.BooksService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(BooksController.PATH)
public class BooksController implements BooksService {

    public static final String PATH = "/books";

    @RequestMapping(method = RequestMethod.GET)
    public List<Book> findAll(){
        Book book = new Book();
        book.setTitle("My book");
        book.setAuthor("My author");
        return Arrays.asList(book);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Book findOne(@PathVariable String id){
        Book book = new Book();
        book.setTitle("My book");
        book.setAuthor("My author");
        return book;
    }
}
