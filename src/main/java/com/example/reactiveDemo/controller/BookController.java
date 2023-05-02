package com.example.reactiveDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.example.reactiveDemo.service.BookService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/books")
public class BookController {
	
	@Autowired
    BookService bookService;

    @GetMapping
    public Flux<Books> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Books>> getBookById(@PathVariable String id) {
        return bookService.getBookById(id);
    }

    @PostMapping
    public Mono<Books> createBook(@RequestBody Books book) {
        return bookService.createBook(book);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Books>> updateBook(@PathVariable String id, @RequestBody Books book) {
        return bookService.updateBook(id, book);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteBook(@PathVariable String id) {
        return bookService.deleteBook(id);
    }
}

