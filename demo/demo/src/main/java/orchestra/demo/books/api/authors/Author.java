package orchestra.demo.books.api.authors;

import orchestra.demo.books.api.Book;

import java.util.ArrayList;
import java.util.List;

public class Author {
    private String name;
    private List<Book> books = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }
}
