package com.example.bookcore.service;

import com.example.bookcore.repository.BookRepository;
import com.example.bookmodel.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Override
    public Flux<Book> allBook() {
        return bookRepository.getAll();
    }

    @Override
    public Flux<Book> getByTitle(String title) {
        return bookRepository.getByTitle(title);
    }

    @Override
    public Mono<Void> create(Mono<Book> book) {
        return bookRepository.create(book);
    }

    @Override
    public Mono<Void> clearAll() {
        return bookRepository.clearAll();
    }

    @Override
    public Mono<Void> update(Mono<Book> book) {
        return bookRepository.update(book);
    }

    @Override
    public Mono<Book> getById(int id) {
        return bookRepository.findById(id);
    }

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

}
