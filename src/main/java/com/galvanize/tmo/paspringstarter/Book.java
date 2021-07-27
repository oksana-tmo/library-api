package com.galvanize.tmo.paspringstarter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String author;
    private String title;
    private Integer datePublished;

    public Book() { }
    public Book(String author, String title, Integer datePublished) {
        this.author = author;
        this.title = title;
        this.datePublished = datePublished;
    }

    public Long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public Integer getDatePublished() {
        return datePublished;
    }

    @Override
    public String toString() {
        return String.format(
                "Book[id=%d, author='%s', title='%s', datePublished='%d']",
                id, author, title, datePublished);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) && author.equals(book.author) && title.equals(book.title) && datePublished.equals(book.datePublished);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, title, datePublished);
    }
}
