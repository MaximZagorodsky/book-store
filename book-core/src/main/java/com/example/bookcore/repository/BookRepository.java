package com.example.bookcore.repository;

import com.example.bookcore.model.Book;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface BookRepository {

    Flux<Book> addAll(Flux<Book> books);

    Flux<Book> getAll();

    Flux<Book> getByTitle(String title);

    Mono<Book> create(Mono<Book> book);

    Mono<Book> update(Mono<Book> book);

    Mono<Book> findById(int id);

    void clearAll();
}
