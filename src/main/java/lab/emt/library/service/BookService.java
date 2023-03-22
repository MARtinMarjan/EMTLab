package lab.emt.library.service;

import lab.emt.library.model.Book;
import lab.emt.library.model.dto.BookDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    Book findBookById(Long id);

    List<Book> listAllBooks();

    Book addBook(BookDto bookDto);

    Book editBook(Long id , BookDto book);

    void deleteBook(Long id);

    void markBookAsTaken(Long id);

    List<Book> getAllBooksByPage(Pageable pageable);
}
