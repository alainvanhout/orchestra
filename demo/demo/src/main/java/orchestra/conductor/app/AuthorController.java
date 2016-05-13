package orchestra.conductor.app;

import orchestra.conductor.books.api.Book;
import orchestra.conductor.books.api.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
public class AuthorController {

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
