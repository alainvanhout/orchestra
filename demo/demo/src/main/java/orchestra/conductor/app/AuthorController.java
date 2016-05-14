package orchestra.conductor.app;

import orchestra.demo.books.api.Book;
import orchestra.demo.books.api.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AuthorController.PATH)
public class AuthorController {

    public static final String PATH = "/authors";
    @Autowired
    private BookService bookService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    private Author getAuthor(){
        Author author = new Author();
        author.setName("My author");
        Book book = bookService.findOne("2");
        author.getBooks().add(book);
        return author;
    }

}
