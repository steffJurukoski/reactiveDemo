package com.example.reactiveDemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.reactiveDemo.model.Books;
import com.example.reactiveDemo.repo.BookRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookService {
	
	@Autowired
	BookRepository bookRepository;
	
	public Flux<Books> getAllBooks() {
        return bookRepository.findAll();
    }

	public Mono<ResponseEntity<Books>> getBookById(String id) {
		return bookRepository.findById(id)
				.map(book -> ResponseEntity.ok(book))
                .defaultIfEmpty(new ResponseEntity("Resource book not found in database", HttpStatus.NOT_FOUND));
    }
	
	public Mono<Books> createBook(Books book) {
        return bookRepository.save(book);
    }
	
	public Mono<ResponseEntity<Books>> updateBook(String id, Books book) {
        return bookRepository.findById(id)
                .flatMap(existingBook -> {
                    existingBook.setTitle(book.getTitle());
                    existingBook.setAuthor(book.getAuthor());
                    return bookRepository.save(existingBook);
                })
                .map(updatedProduct -> new ResponseEntity<>(updatedProduct, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity("Resource book not found in database", HttpStatus.NOT_FOUND));
    }
	
	public Mono<ResponseEntity<Void>> deleteBook(String id) {
        return bookRepository.deleteById(id)
        		.then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)))
                .onErrorResume(error -> Mono.just(new ResponseEntity<>(HttpStatus.NOT_FOUND)));
    }
}
