package com.yue.bookie.server.lib.repository;

import com.yue.bookie.server.lib.struct.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepo extends JpaRepository<Book, Long> {
    List<Book> findByTitleContaining(String title);

    List<Book> findByAuthorContaining(String author);

    @Query(value = "SELECT b FROM book_library lib WHERE locate(:keyword, lib.title) >= 0 OR locate(:keyword, lib.author) >= 0", nativeQuery = true)
    List<Book> ambiguousFind(@Param("keyword") String keyword);

    List<Book> findByType(String type);

    Book findByIsbn(String isbn);
}
