package domain;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Attachment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer attachment_id;
    @Column(unique = false)
    private String name;
    private byte[] data;
    private String type;

    @OneToOne(mappedBy = "attachment")
    private transient Book book;
}