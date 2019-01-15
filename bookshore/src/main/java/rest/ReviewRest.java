package rest;

import domain.Book;
import domain.Review;
import domain.Reviewer;

import java.util.Date;
import java.util.List;

public class ReviewRest {
    private String name;
    private String email;
    private String bookReview;
    private Date date;
    private Reviewer reviewer;
    private Book book;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBookReview() {
        return bookReview;
    }

    public Date getDate() {
        return date;
    }

    public Reviewer getReviewer() {
        return reviewer;
    }

    public Book getBook() {
        return book;
    }
}
