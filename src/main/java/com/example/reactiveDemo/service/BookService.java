package com.example.reactiveDemo.service;

import org.springframework.stereotype.Service;

import com.example.reactiveDemo.model.Books;
import com.example.reactiveDemo.repo.BookRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookService {
	
	private final BookRepository bookRepository;
	
	public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
	
	
	public Flux<Books> getAllBooks() {
        return bookRepository.findAll();
    }

	public Mono<Books> getBookById(String id) {
        return bookRepository.findById(id);
    }
	
	public Mono<Books> createBook(Books book) {
        return bookRepository.save(book);
    }
	
	public Mono<Books> updateBook(String id, Books book) {
        return bookRepository.findById(id)
                .flatMap(existingBook -> {
                    existingBook.setTitle(book.getTitle());
                    existingBook.setAuthor(book.getAuthor());
                    return bookRepository.save(existingBook);
                });
    }
	
	public Mono<Void> deleteBook(String id) {
        return bookRepository.deleteById(id);
    }
}
