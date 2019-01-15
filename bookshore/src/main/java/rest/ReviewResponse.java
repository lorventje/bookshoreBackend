package rest;

import com.google.gson.Gson;
import domain.Book;
import domain.Review;

import java.util.List;

public class ReviewResponse {
    private static final Gson gson = new Gson();

    public static String getReviewsById(List<Review> reviews){
        RestResponse response = new RestResponse();
        response.setBooks(null);
        response.setBook(null);
        response.setReviews(reviews);
        String output = gson.toJson(response);
        System.out.println("[Server response] " + output);
        return output;
    }
}
