package lab.emt.library.model;

import jakarta.persistence.*;
import lab.emt.library.model.enumerations.Category;
import lombok.Data;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Enumerated
    private Category category;
    @ManyToOne
    private Author author;

    private int availableCopies;

    public Book() {
    }
}
