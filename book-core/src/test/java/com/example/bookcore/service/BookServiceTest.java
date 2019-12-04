package com.example.bookcore.service;

import com.example.bookcore.model.Book;
import com.example.bookcore.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
public class BookServiceTest {
    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;

    private Book langoliers;
    private Book nightmare;

    @BeforeTestClass
    public void init() {
        langoliers = new Book(2, "Stephen Edwin King", "The Langoliers");
        nightmare = new Book(3, "Stephen Edwin King", "The Nightmare");
    }

    @Test
    public void testCreate() {

        bookService.create(Mono.just(langoliers));
        StepVerifier
                .create(bookRepository.findById(langoliers.getId()))
                .expectNextMatches(e -> e.getId() == langoliers.getId())
                .expectComplete()
                .verify();
    }

    @Test
    public void testUpdate() {
        bookRepository.clearAll();
        bookRepository.clearAll();
        bookRepository.addAll(Flux.just(langoliers, nightmare));
        Book updatedLangoliers = new Book(2, "Stephen Edwin King", "The Langoliers2");
        bookService.update(Mono.just(updatedLangoliers));
        StepVerifier
                .create(bookRepository.findById(2))
                .expectNextMatches(e -> e.getTitle() == updatedLangoliers.getTitle())
                .expectComplete()
                .verify();
    }


    @Test
    public void testFindBy() {
        bookRepository.clearAll();
        bookRepository.clearAll();
        bookRepository.addAll(Flux.just(langoliers));

        bookService.findById(2);
        StepVerifier
                .create(bookRepository.findById(langoliers.getId()))
                .expectNextMatches(e -> e.getTitle() == langoliers.getTitle())
                .expectComplete()
                .verify();
    }

}
