package domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
public class Reviewer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reviewer_id;
    @Column(unique = false)
    private String name;
    private String email;

    @OneToMany(mappedBy = "reviewer", cascade = CascadeType.PERSIST)
    private transient List<Review> reviews;

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }
}
