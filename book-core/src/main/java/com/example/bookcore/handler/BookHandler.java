package com.example.bookcore.handler;

import com.example.bookcore.service.BookService;
import com.example.bookmodel.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class BookHandler {
    private BookService bookService;

    public Mono<ServerResponse> clearAll(ServerRequest request) {
        return ServerResponse
                .ok()
                .build(bookService.clearAll());
    }

    public Mono<ServerResponse> createBook(ServerRequest request) {
        Mono<Book> book = request.bodyToMono(Book.class);
        return ServerResponse
                .ok()
                .build(bookService.create(book));
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        Mono<Void> book = bookService.update(request.bodyToMono(Book.class));
        return ServerResponse
                .ok()
                .build(book);
    }

    public Mono<ServerResponse> getByTitle(ServerRequest request) {
        Flux<Book> books = bookService.getByTitle(request.pathVariable("title"));
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(books, Book.class);
    }

    public Mono<ServerResponse> getById(ServerRequest request) {
        Mono<Book> book = bookService.getById(Integer.parseInt(request.pathVariable("id")));
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(book, Book.class);
    }

    public Mono<ServerResponse> listBook(ServerRequest request) {
        Flux<Book> books = bookService.allBook();
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(books, Book.class);
    }

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }
}
