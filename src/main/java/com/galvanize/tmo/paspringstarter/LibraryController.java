package com.galvanize.tmo.paspringstarter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LibraryController {

    @Autowired
    Library library;

    @GetMapping("/health")
    public String health() {
        return "UP";
    }

    @PostMapping(value = "/api/books")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Book addToLibrary(@RequestBody Book book){
        if (book != null){
            Book savedBook = library.save(book);
            return savedBook;
        }
        return null;
    }

    @GetMapping(value = "/api/books")
    public String getAllBooks() {
        List<Book> books = library.findByOrderByTitleAsc();
        return createLibraryPayload(books);
    }

    @DeleteMapping(value = "/api/books")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAllBooks() {
        library.deleteAll();
    }

    private String createLibraryPayload(List<Book> bookList){
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode books  = objectMapper.createObjectNode();

        JsonNode listNode = objectMapper.valueToTree(bookList);
        books.set("books", listNode);
        try {
            return objectMapper.writeValueAsString(books);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";

    }
}
