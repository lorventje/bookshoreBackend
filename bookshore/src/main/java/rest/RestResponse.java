package rest;

import domain.Book;
import domain.Review;
import domain.Reviewer;

import java.util.List;

public class RestResponse {
    private List<Book> books;
    private Book book;
    private List<Review> reviews;
    private Review review;
    private Reviewer reviewer;

    public RestResponse() {}

    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
        this.book = book;
    }

    public List<Book> getBooks() {
        return books;
    }
    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Review> getReviews() {
        return reviews;
    }
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Review getReview() {
        return review;
    }
    public void setReview(Review review) {
        this.review = review;
    }

    public Reviewer getReviewer() {
        return reviewer;
    }
    public void setReviewer(Reviewer reviewer) {
        this.reviewer = reviewer;
    }
}
