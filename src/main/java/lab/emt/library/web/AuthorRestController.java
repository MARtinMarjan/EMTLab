package lab.emt.library.web;

import lab.emt.library.model.Author;
import lab.emt.library.model.dto.AuthorDto;
import lab.emt.library.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/authors")
public class AuthorRestController {
    private final AuthorService authorService;

    public AuthorRestController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        Author author = this.authorService.findAuthorById(id);

        if (author == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(author);
        }
    }

    @GetMapping()
    public List<Author> listAuthors() {
        return this.authorService.listAllAuthors();
    }


    @PostMapping("/add")
    public ResponseEntity<Author> addAuthor(@RequestBody AuthorDto authorDto) {
        Author newAuthor = this.authorService.addAuthor(authorDto);
        if (newAuthor == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(newAuthor);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Author> editAuthor(@PathVariable Long id, @RequestBody AuthorDto authorDto) {
        Author newAuthor = this.authorService.editAuthor(id, authorDto);
        if (newAuthor == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(newAuthor);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        Author author = this.authorService.findAuthorById(id);
        if (author == null) {
            return ResponseEntity.notFound().build();
        } else {
            authorService.deleteAuthor(id);
            return ResponseEntity.ok(author);
        }
    }
}
