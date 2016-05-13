package orchestra.conductor.books.proxy;

import ochestra.proxying.RequestService;
import orchestra.conductor.books.api.Book;
import orchestra.conductor.books.api.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceProxy implements BookService {

    @Autowired
    private RequestService requestService;

    @Override
    public Book findOne(String id) {
        return requestService.retrieve("http://localhost:8080/books/" + id, "GET", Book.class);
    }
}
