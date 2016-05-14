package orchestra.conductor.app;

import orchestra.demo.books.api.Book;
import orchestra.demo.books.api.BooksService;
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
        Author author = new Author();
        author.setName("My author");
        List<Book> books = booksService.findAll();
        author.getBooks().addAll(books);
        return author;
    }

}
