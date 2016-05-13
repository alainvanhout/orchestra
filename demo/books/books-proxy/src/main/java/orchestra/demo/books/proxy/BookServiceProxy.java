package orchestra.demo.books.proxy;

import ochestra.proxying.RequestService;
import orchestra.demo.books.api.Book;
import orchestra.demo.books.api.BookService;
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
