import domain.Book;
import org.junit.Test;
import rest.RestClient;
import rest.RestResponse;

import java.util.List;

public class BookTest {

    @Test
    public void addBookTest(){
        String title = "Wiskunde met getallen";
        Integer pages = 200;
        String ISBN = "3682637463728";

        RestClient restClient = new RestClient();

        Book book = new Book(title, pages, ISBN);
        String queryPost = "/addBook";
        RestResponse restResponse = restClient.executeQueryPost(book, queryPost);
    }

    @Test
    public void AllBooksTest(){
        List<Book> books = null;
        RestClient restClient = new RestClient();
        String queryGet = "/allBooks";
        RestResponse restResponse = restClient.executeQueryGet(queryGet);

        books = restResponse.getBooks();
        if(books != null) {
            System.out.println("Successful to get all books");
        } else {
            System.out.println("Failed to get all books.");
        }
    }
}
