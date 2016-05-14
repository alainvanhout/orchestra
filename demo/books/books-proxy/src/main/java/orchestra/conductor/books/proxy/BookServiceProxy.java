package orchestra.conductor.books.proxy;

import ochestra.proxying.RequestService;
import orchestra.conductor.proxy.ConductorService;
import orchestra.demo.books.api.Book;
import orchestra.demo.books.api.BookService;
import orchestra.instrument.identity.ServiceIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceProxy implements BookService {

    @Autowired
    private RequestService requestService;

    @Autowired
    private ConductorService conductorService;

    @Override
    public Book findOne(String id) {
        ServiceIdentity booksService = conductorService.requisition(new ServiceIdentity().service("books"));
        String url = "http://" + booksService.getHost() + ":" + booksService.getPort() + booksService.getPath() + "/" + id;
        return requestService.retrieve(url, "GET", Book.class);
    }
}
