package com.example.bookcore.service;

import com.example.bookcore.model.Book;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookService {
    Flux<Book> addAll(Flux<Book> books);

    Flux<Book> allBook();

    Flux<Book> getByTitle(String title);

    Mono<Book> create(Mono<Book> book);// Should it be Mono<Book> or just Book, I suppose Mono so far

    Mono<Book> update(Mono<Book> book);

    Mono<Book> findById(int id);

}
