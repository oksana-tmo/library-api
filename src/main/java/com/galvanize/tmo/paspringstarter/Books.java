package com.galvanize.tmo.paspringstarter;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Books {
    private HashMap<Long, Book> bookMap = new HashMap<>();

    private Long getNextId(){
        Long bookId = (long) (bookMap.size() + 1);
        return bookId;
    }


    Book addBook(Book book){
        Long id = getNextId();
        book.setId(id);
        bookMap.put(id, book);

        return bookMap.get(id);
    }
    Collection<Book> getAllBooks() {
        return bookMap.values();
    }

    void deleteAll(){
        bookMap.clear();
    }
}
