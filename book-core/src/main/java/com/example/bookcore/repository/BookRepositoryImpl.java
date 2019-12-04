package com.example.bookcore.repository;

import com.example.bookcore.model.Book;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
public class BookRepositoryImpl implements BookRepository {
    private Flux<Book> books = Flux.just(new Book(1, "Nikolai Chernyshevsky", "What to do?"));

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
        return books.filter(book -> book.getTitle().equals(title));
    }

    @Override
    public Mono<Book> create(Mono<Book> book) {
        books = books.mergeWith(book);
        return book;
    }

    @Override
    public Mono<Book> update(Mono<Book> book) {
        book.subscribe(e ->
                books = books
                        .filter(b -> e.getId() != b.getId())
                        .mergeWith(book)
        );
        return book;
    }

    @Override
    public Mono<Book> findById(int id) {
        return Mono.from(books.filter(e -> e.getId() == id));
    }

    @Override
    public void clearAll() {
        books = Flux.empty();
    }
}
