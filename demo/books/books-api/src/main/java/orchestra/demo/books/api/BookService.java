package orchestra.demo.books.api;

import orchestra.demo.books.api.Book;

public interface BookService {

    Book findOne(String id);
}
