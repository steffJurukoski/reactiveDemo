package com.example.reactiveDemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.reactiveDemo.model.Books;
import com.example.reactiveDemo.repo.BookRepository;
import com.example.reactiveDemo.exception.ResourceNotFoundException;

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
				.switchIfEmpty(Mono.error(new ResourceNotFoundException("Book with id " + id + " not found")));
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
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Book with id " + id + " not found")));
    }
	
	public Mono<Void> deleteBook(String id) {
		Mono<Books> book = bookRepository.findById(id)
				.switchIfEmpty(Mono.error(new ResourceNotFoundException("Book with id " + id + " not found")));
		
		Mono<String> idToDelete = book.map(books -> books.get_id());
		return bookRepository.deleteById(idToDelete);
    }
}
