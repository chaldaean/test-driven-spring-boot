package com.xpinjection.springboot.dao;

import com.xpinjection.springboot.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Alimenkou Mikalai
 */
public interface BookDao extends JpaRepository<Book, Long> {
    List<Book> findByAuthor(String author);
}
