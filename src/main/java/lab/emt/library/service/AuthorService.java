package lab.emt.library.service;

import lab.emt.library.model.Author;
import lab.emt.library.model.dto.AuthorDto;

import java.util.List;

public interface AuthorService {
    Author findAuthorById(Long id);

    List<Author> listAllAuthors();
    Author addAuthor(AuthorDto authorDto);

    Author editAuthor(Long id, AuthorDto authorDto);

    void deleteAuthor(Long id);
}
