package com.example.bookcore.repository;

import com.example.bookmodel.model.Book;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface BookRepository {

    Flux<Book> addAll(Flux<Book> books);

    Flux<Book> getAll();

    Flux<Book> getByTitle(String title);

    Mono<Void> create(Mono<Book> book);

    Mono<Void> update(Mono<Book> book);

    Mono<Book> findById(int id);

    Mono<Void> clearAll();
}
