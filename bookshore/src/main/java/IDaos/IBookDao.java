package IDaos;

import domain.Book;

import java.util.List;

public interface IBookDao {
    void create(Book book);
    List<Book> getAllBooks();
    List<Book> findBooksByTitle(String title);
    Book findBookById(Integer id);
}
