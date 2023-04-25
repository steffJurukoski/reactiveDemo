package com.example.reactiveDemo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.reactiveDemo.model.Books;
import com.example.reactiveDemo.repo.BookRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public Flux<Books> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Books> getBookById(@PathVariable String id) {
        return bookRepository.findById(id);
    }

    @PostMapping
    public Mono<Books> createBook(@RequestBody Books book) {
        return bookRepository.save(book);
    }

    @PutMapping("/{id}")
    public Mono<Books> updateBook(@PathVariable String id, @RequestBody Books book) {
        return bookRepository.findById(id)
                .flatMap(existingBook -> {
                    existingBook.setTitle(book.getTitle());
                    existingBook.setAuthor(book.getAuthor());
                    return bookRepository.save(existingBook);
                });
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteBook(@PathVariable String id) {
        return bookRepository.deleteById(id);
    }
}

