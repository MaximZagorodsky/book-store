package com.example.bookmiddleware.handler;

import com.example.bookmodel.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Component
public class BookHandler {
    @Value("${app-service-server-url}")
    private String appServiceUrl;
    private WebClient webClient;

    public Mono<ServerResponse> createBook(ServerRequest request) {
        Mono<Book> newBook = request.bodyToMono(Book.class);

        Mono<Book> book = webClient
                .post()
                .uri(appServiceUrl, newBook)
                .retrieve()
                .bodyToMono(Book.class);

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(book, Book.class);
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        Mono<Book> book = request.bodyToMono(Book.class);

        Mono<Book> updatedBook = webClient
                .patch()
                .uri(appServiceUrl + "/book", book)
                .retrieve()
                .bodyToMono(Book.class);

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(book, Book.class);
    }

    public Mono<ServerResponse> getByTitle(ServerRequest request) {
        String title = request.pathVariable("title");

        Flux<Book> book = webClient
                .get()
                .uri(appServiceUrl + "/title/{title}", title)
                .retrieve()
                .bodyToFlux(Book.class);

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(book, Book.class);
    }

    public Mono<ServerResponse> getById(ServerRequest request) {
        String id = request.pathVariable("id");

        Flux<Book> book = webClient
                .get()
                .uri(appServiceUrl + "/{id}", id)
                .retrieve()
                .bodyToFlux(Book.class);
        book.subscribe(System.out::println);
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(book, Book.class);
    }

    public Mono<ServerResponse> listBook(ServerRequest request) {
        Flux<Book> books = webClient
                .get()
                .uri(appServiceUrl)
                .retrieve()
                .bodyToFlux(Book.class);

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(books, Book.class);
    }

    @Autowired
    public void setWebClient(WebClient webClient) {
        this.webClient = webClient;
    }
}
