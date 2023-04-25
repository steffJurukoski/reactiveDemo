package com.example.reactiveDemo.repo;

import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.reactiveDemo.model.Books;

public interface BookRepository extends ReactiveCrudRepository<Books, String> {

}

