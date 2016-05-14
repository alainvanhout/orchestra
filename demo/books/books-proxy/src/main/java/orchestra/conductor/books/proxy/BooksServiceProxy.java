package orchestra.conductor.books.proxy;

import ochestra.proxying.request.Endpoint;
import ochestra.proxying.request.RequestService;
import orchestra.conductor.proxy.ConductorService;
import orchestra.demo.books.api.Book;
import orchestra.demo.books.api.BooksService;
import orchestra.instrument.identity.ServiceIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class BooksServiceProxy implements BooksService {

    @Autowired
    private RequestService requestService;

    @Autowired
    private ConductorService conductorService;

    private Endpoint endpoint;

    @PostConstruct
    private void init(){
        ServiceIdentity identity = new ServiceIdentity().service("books").version("0.0.1");
        endpoint = new Endpoint(conductorService.requisition(identity)).requestService(requestService);
    }

    @Override
    public Book findOne(String id) {
        return endpoint.send(null, "/" + id, "GET").toObject(Book.class);
    }

    @Override
    public List<Book> findAll() {
        return endpoint.send(null, "", "GET").toList(Book.class);
    }
}
