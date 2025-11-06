package com.example.tpgraphql.resolver;

import com.example.tpgraphql.domain.Book;
import com.example.tpgraphql.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;
import org.springframework.format.annotation.DateTimeFormat;

@Controller
public class BookResolver {

    private final BookService bookService;

    public BookResolver(BookService bookService) {
        this.bookService = bookService;
    }

    @QueryMapping
    public List<Book> books() {
        return bookService.getAllBooks();
    }

    @QueryMapping
    public Optional<Book> bookById(@Argument Long id) {
        return bookService.getBookById(id);
    }

    @MutationMapping
    public Book createBook(@Argument("input") BookInput input) {
        Book book = new Book();
        book.setTitle(input.getTitle());
        book.setAuthor(input.getAuthor());
        book.setPublishedDate(java.time.LocalDate.parse(input.getPublishedDate()));
        book.setPrice(input.getPrice());
        return bookService.createBook(book);
    }

    @MutationMapping
    public Optional<Book> updateBook(@Argument Long id, @Argument("input") BookInput input) {
        Book updatedBook = new Book();
        updatedBook.setTitle(input.getTitle());
        updatedBook.setAuthor(input.getAuthor());
        updatedBook.setPublishedDate(java.time.LocalDate.parse(input.getPublishedDate()));
        updatedBook.setPrice(input.getPrice());
        return bookService.updateBook(id, updatedBook);
    }

    // DTO interne pour BookInput
    public static class BookInput {
        private String title;
        private String author;
        private String publishedDate;
        private Double price;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getPublishedDate() {
            return publishedDate;
        }

        public void setPublishedDate(String publishedDate) {
            this.publishedDate = publishedDate;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }
    }

    @MutationMapping
    public boolean deleteBook(@Argument Long id) {
        return bookService.deleteBook(id);
    }
}