package com.galvanize.tmo.paspringstarter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class LibraryController {
    private Books library = new Books();

    @GetMapping("/health")
    public String health() {
        return "UP";
    }

    @PostMapping(value = "/api/books")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Book addToLibrary(@RequestBody Book book){
        if (book != null){
            return library.addBook(book);
        }
        return null;
    }

    @GetMapping(value = "/api/books")
    public String getAllBooks() {

        List<Book> books = new ArrayList<>();
        books.addAll(library.getAllBooks());
        Collections.sort(books);
        return createLibraryPayload(books);
    }

    @DeleteMapping(value = "/api/books")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAllBooks() {
        library.deleteAll();
    }

    private String createLibraryPayload(Collection<Book> bookList){
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
