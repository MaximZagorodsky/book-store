package com.example.bookcore.router;

import com.example.bookcore.handler.BookHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class BookRouter {
    @Bean
    public RouterFunction<ServerResponse> route(BookHandler handler) {
        return RouterFunctions.route()
                .path("/book", builder -> builder
                        .GET("/{title}", accept(APPLICATION_JSON), handler::getByTitle)
                        .GET("", accept(APPLICATION_JSON), handler::listBook)
                        .PATCH("/book", handler::update))
                .build();
    }
}