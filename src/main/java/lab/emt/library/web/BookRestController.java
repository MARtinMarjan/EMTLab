package lab.emt.library.web;

import lab.emt.library.model.Book;
import lab.emt.library.model.dto.BookDto;
import lab.emt.library.service.BookService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookRestController {
    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = this.bookService.findBookById(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(book);
        }
    }

    @GetMapping("/all")
    public List<Book> listBooks() {
        return this.bookService.listAllBooks();
    }


    @GetMapping("/pagination")
    public List<Book> findAllWithPagination(Pageable pageable) {
        return this.bookService.getAllBooksByPage(Pageable.ofSize(3).withPage(pageable.getPageNumber()));
    }


    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody BookDto bookDto) {
        Book book = this.bookService.addBook(bookDto);
        if (book == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(book);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> edit(@PathVariable Long id, @RequestBody BookDto bookDto) {
        Book book = this.bookService.editBook(id, bookDto);
        if (book == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(book);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id) {
        Book book = bookService.findBookById(id);

        if (book == null) {
            return ResponseEntity.notFound().build();
        } else {
            this.bookService.deleteBook(id);
            return ResponseEntity.ok(book);
        }
    }

    @PutMapping("/marked/{id}")
    public ResponseEntity<Book> markBookAsTaken(@PathVariable Long id) {
        Book book = bookService.findBookById(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        } else {
            this.bookService.markBookAsTaken(id);
            return ResponseEntity.ok(book);
        }
    }
}
