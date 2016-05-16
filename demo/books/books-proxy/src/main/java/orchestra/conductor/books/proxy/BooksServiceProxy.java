package orchestra.conductor.books.proxy;

import ochestra.proxying.request.Endpoint;
import ochestra.proxying.request.RequestService;
import orchestra.conductor.proxy.ConductorService;
import orchestra.demo.books.api.Book;
import orchestra.demo.books.api.BooksService;
import orchestra.instrument.identity.ServiceIdentity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksServiceProxy implements BooksService {

    @Autowired
    private RequestService requestService;

    @Autowired
    private ConductorService conductorService;

    private Endpoint endpoint;

    //    @PostConstruct
    private void init() {
        System.out.println("Looking up identity for book service proxy");
        ServiceIdentity identity = new ServiceIdentity().service("books").version("0.0.1");
        System.out.println("Collected identity for book service proxy: " + ToStringBuilder.reflectionToString(identity, ToStringStyle.MULTI_LINE_STYLE));
        if (identity != null) {
            ServiceIdentity requisition = conductorService.requisition(identity);
            if (requisition == null) {
                throw new IllegalStateException("Could not requisition service identity:" + ToStringBuilder.reflectionToString(identity, ToStringStyle.MULTI_LINE_STYLE));
            }
            endpoint = new Endpoint(requisition).requestService(requestService);
            System.out.println("Created endpoint reference for book service proxy");
        }
    }

    @Override
    public Book findOne(String id) {
        if (endpoint == null) {
            init();
        }
        return endpoint.send(null, "/" + id, "GET").toObject(Book.class);
    }

    @Override
    public List<Book> findAll() {
        if (endpoint == null) {
            init();
        }
        return endpoint.send(null, "", "GET").toList(Book.class);
    }
}
