package domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
public class Publisher implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer publisher_id;
    @Column(unique = false)
    private String name;

    @OneToMany(mappedBy = "publisher")
    private transient List<Book> books;
}