package com.example.bookcore.service;

import com.example.bookmodel.model.Book;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookService {
    Flux<Book> allBook();

    Flux<Book> getByTitle(String title);

    Mono<Void> create(Mono<Book> book);

    Mono<Void> update(Mono<Book> book);

    Mono<Book> getById(int id);

    Mono<Void> clearAll();

}
