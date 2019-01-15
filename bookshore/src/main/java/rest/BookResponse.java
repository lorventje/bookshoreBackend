package rest;

import com.google.gson.Gson;
import domain.Book;

import java.util.ArrayList;
import java.util.List;

public class BookResponse {
    private static final Gson gson = new Gson();

    public static String getAllBooks(List<Book> books){
        RestResponse response = new RestResponse();
        response.setBooks(books);
        String output = gson.toJson(response);
        System.out.println("[Server response] " + output);
        return output;
    }

    public static String getSingleBook(Book book){
        RestResponse restResponse = new RestResponse();
        restResponse.setBook(book);
        String output = gson.toJson(restResponse);
        System.out.println("[Server response] " + output);
        return output;
    }
}
