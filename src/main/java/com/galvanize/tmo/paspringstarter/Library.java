package com.galvanize.tmo.paspringstarter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface Library extends JpaRepository<Book, Long> {

    public List<Book> findByOrderByTitleAsc();

}
