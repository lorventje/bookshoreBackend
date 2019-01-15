package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
public class Author implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer author_id;
    @Column(unique = false)
    private String name;
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @OneToMany(mappedBy = "author")
    private transient List<Book> books;
}