package com.example.bookcore.repository;

import com.example.bookmodel.model.Book;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
public class BookRepositoryImpl implements BookRepository {
    private Flux<Book> books = Flux.just(new Book(1, "Nikolai Chernyshevsky", "What to do"));

    @Override
    public Flux<Book> addAll(Flux<Book> books) {
        this.books = this.books.concatWith(books);
        return books;
    }

    @Override
    public Flux<Book> getAll() {
        return books;
    }

    @Override
    public Flux<Book> getByTitle(String title) {
        return books
                .filter(book -> book.getTitle().equals(title)).switchIfEmpty(Flux.empty());
    }

    @Override
    public Mono<Void> create(Mono<Book> book) {
        book.subscribe(b -> {
            System.out.println("Book has been created: " + b);
            books = books.concatWith(Flux.just(b));
        });
        return Mono.empty();
    }

    @Override
    public Mono<Void> update(Mono<Book> book) {
        book.subscribe(b -> {
                    books = books
                            .filter(e -> e.getId() != b.getId())
                            .concatWith(Flux.just(b));
                    System.out.println("Book has been updated to: " + b);
                }
        );
        return Mono.empty();
    }

    @Override
    public Mono<Book> findById(int id) {
        return Mono.from(books.filter(e -> e.getId() == id)).switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<Void> clearAll() {
        books = Flux.empty();
        return Mono.empty();
    }
}
