package domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "Review.getReviewsById", query = "select r from Review as r where r.book.book_id = :id")
})
public class Review implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer review_id;
    @Column(unique = false)
    private String bookReview;
    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne
    private Book book;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Reviewer reviewer;

    public String getBookReview() {
        return bookReview;
    }

    public Date getDate() {
        return date;
    }

    public Book getBook() {
        return book;
    }

    public Reviewer getReviewer() {
        return reviewer;
    }

    public void setReviewer(Reviewer reviewer) {
        this.reviewer = reviewer;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setBookReview(String bookReview) {
        this.bookReview = bookReview;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
