package integration.com.example.bookmiddleware.handler;

import com.example.bookmiddleware.BookMiddlewareApplication;
import com.example.bookmodel.model.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.stream.Stream;

@SpringBootTest(classes = BookMiddlewareApplication.class)
public class BookHandlerTest {
    @Value("${zuul-service-server-url}")
    private String baseUrl;
    @Value("${app-service-server-url}")
    private String appServiceUrl;

    private WebTestClient testClient = WebTestClient.bindToServer()
            .baseUrl(baseUrl)
            .build();

    @BeforeEach
    public void clearDummyRepository() {
        testClient.patch()
                .uri(appServiceUrl + "/clear")
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void testGetUsers() {
        addBooks();
        testClient.get()
                .uri(appServiceUrl)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(Book.class)
                .value(userResponse -> {
                            userResponse
                                    .stream()
                                    .forEach(e -> System.out.println("\033[4;34m Recieved: " + e + "\033[4;34m"));
                        }
                )
                .hasSize(4);

    }

    public void addBooks() {
        Stream books = Stream.of(
                new Book(0, "Author0", "Title0"),
                new Book(1, "Author1", "Title1"),
                new Book(2, "Author2", "Title2"),
                new Book(3, "Author3", "Title3"));

        books.forEach(book -> {
            testClient.post()
                    .uri(appServiceUrl + "/book")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .bodyValue(book)
                    .exchange();
        });
    }

    @Test
    public void testCreateBook() {
        Book book = new Book(3, "Popular Author", "Be popular in Action");

        testClient.post()
                .uri(appServiceUrl + "/book")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(book)
                .exchange()
                .expectStatus()
                .isOk();

        testClient.get()
                .uri(appServiceUrl + "/3")
                .exchange()
                .expectBody(Book.class)
                .value(userResponse -> {
                            System.out.println("\033[4;34m Created: " + userResponse + "\033[4;34m");
                            Assertions.assertThat(userResponse.getId()).isEqualTo(3);
                        }
                );
    }


    @Test
    public void testUpdateBook() {
        Book book = new Book(0, "Popular Author2", "Be popular in Action");

        testClient.patch()
                .uri(appServiceUrl + "/book")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(book)
                .exchange()
                .expectStatus()
                .isOk();

        testClient.get()
                .uri(appServiceUrl + "/0")
                .exchange()
                .expectBody(Book.class)
                .value(userResponse -> {
                            System.out.println("\033[4;34m Recieved updated: " + userResponse + "\033[4;34m");
                            Assertions.assertThat(userResponse.getId()).isEqualTo(0);
                            Assertions.assertThat(userResponse.getAuthor()).isEqualTo(book.getAuthor());
                        }
                );
    }
}
