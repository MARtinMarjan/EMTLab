package lab.emt.library.service.impl;

import lab.emt.library.model.Author;
import lab.emt.library.model.Book;
import lab.emt.library.model.dto.BookDto;
import lab.emt.library.repository.BookRepository;
import lab.emt.library.service.AuthorService;
import lab.emt.library.service.BookService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    @Override
    public Book findBookById(Long id) {
        return this.bookRepository.findById(id).orElse(null);
    }

    @Override
    public List<Book> listAllBooks() {
        return this.bookRepository.findAll();
    }

    @Override
    public Book addBook(BookDto bookDto) {
        Book book = new Book();
        return saveBook(bookDto, book);
    }

    @Override
    public Book editBook(Long id, BookDto bookDto) {
        Book book = bookRepository.findById(id).orElse(null);

        if (book == null) {
            return null;
        }

        return saveBook(bookDto, book);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void markBookAsTaken(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book == null) {
            return;
        }

        if(book.getAvailableCopies() > 0){
            book.setAvailableCopies(book.getAvailableCopies() - 1);
            this.bookRepository.save(book);
        }else{
            return;
        }

    }

    @Override
    public List<Book> getAllBooksByPage(Pageable pageable) {
        return this.bookRepository.findAll(pageable).getContent();
    }

    private Book saveBook(BookDto book, Book b) {
        Author author = this.authorService.findAuthorById(book.getAuthor());
        if (author == null) {
            return null;
        }
        b.setName(book.getName());
        b.setCategory(book.getCategory());
        b.setAuthor(author);
        b.setAvailableCopies(book.getAvailableCopies());
        return bookRepository.save(b);
    }
}
