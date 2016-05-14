package orchestra.demo.books.api;

import orchestra.demo.books.api.Book;

import java.util.List;

public interface BooksService {

    Book findOne(String id);

    List<Book> findAll();
}
