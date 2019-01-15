package domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "Book.findBookById", query = "select b from Book as b where b.book_id = :bookId"),
        @NamedQuery(name = "Book.findBooksByTitle", query = "select b from Book as b where b.title like CONCAT('%', :title, '%')")
})
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer book_id;
    @Column(unique = false)
    private String title;
    private Integer pages;
    private String ISBN;

    @OneToMany(mappedBy = "book")
    private transient List<Review> reviews;

    @ManyToOne
    private Author author;
    @ManyToOne
    private Publisher publisher;

    @OneToOne
    private Attachment attachment;

    public Book(){

    }

    public Book(String title, Integer pages, String ISBN){
        this.title = title;
        this.pages = pages;
        this.ISBN = ISBN;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setPages(Integer pages) {
        this.pages = pages;
    }
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }
    public String getISBN() {
        return ISBN;
    }
    public Integer getPages() {
        return pages;
    }
    public Integer getBook_id() {
        return book_id;
    }
}
