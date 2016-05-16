package orchestra.conductor.app;

import orchestra.demo.books.api.Book;
import orchestra.demo.books.api.BooksService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(AuthorController.PATH)
public class AuthorController {

    public static final String PATH = "/authors";

    @Autowired
    private BooksService booksService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    private Author getAuthor(){
        try {
            Author author = new Author();
            author.setName("My author");
            List<Book> books = booksService.findAll();
            author.getBooks().addAll(books);
            return author;
        } catch (Exception e){
            Author author = new Author();
            author.setName(e.getMessage());
            Book book = new Book();
            book.setTitle(ExceptionUtils.getStackTrace(e));
            author.getBooks().add(book);
            return author;
        }
    }

}
