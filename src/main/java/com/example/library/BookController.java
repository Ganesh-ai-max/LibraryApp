package com.example.library;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookRepository repo;

    public BookController(BookRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Book> all(@RequestParam(required = false) String q) {
        if (q == null || q.isBlank()) return repo.findAll();
        return repo.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(q, q);
    }

    @PostMapping
    public Book add(@RequestBody Book b) {
        b.setId(null);
        b.setAvailable(true);
        return repo.save(b);
    }

    @PutMapping("/{id}/toggle")
    public ResponseEntity<Book> toggle(@PathVariable Long id) {
        return repo.findById(id)
                .map(b -> {
                    b.setAvailable(!b.isAvailable());
                    return ResponseEntity.ok(repo.save(b));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
