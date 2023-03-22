package lab.emt.library.model.dto;

import lab.emt.library.model.enumerations.Category;
import lombok.Data;

@Data
public class BookDto {
    private String name;

    private Category category;

    private Long author;

    private Integer availableCopies;
}
