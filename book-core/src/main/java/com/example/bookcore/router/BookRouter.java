package com.example.bookcore.router;

import com.example.bookcore.handler.BookHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.web.reactive.function.server.RouterFunctions;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class BookRouter {
    @Bean
    public HttpHandler route(BookHandler handler) {
        return RouterFunctions.toHttpHandler(RouterFunctions.route()
                .path("/core/book", builder -> builder
                        .GET("title/{title}", accept(APPLICATION_JSON), handler::getByTitle)
                        .GET("/{id}", accept(APPLICATION_JSON), handler::getById)
                        .GET("", accept(APPLICATION_JSON), handler::listBook)
                        .PATCH("/book", accept(APPLICATION_JSON), handler::update)
                        .PATCH("/clear", handler::clearAll)
                        .POST("/book", handler::createBook))
                .build());
    }
}