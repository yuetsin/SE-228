package com.yue.bookie.server.lib.repository;

import com.yue.bookie.server.lib.struct.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepo extends JpaRepository<Book, Long> {
    public List<Object[]> findByTitleContaining(String title);

    public List<Object[]> findByAuthorContaining(String author);

    @Query(value = "SELECT b FROM book_library lib WHERE locate(:keyword, lib.title) >= 0 OR locate(:keyword, lib.author) >= 0", nativeQuery = true)
    public List<Object[]> ambiguousFind(@Param("keyword") String keyword);

    public List<Object[]> findByType(String type);

    public Object findByIsbn(String isbn);
}
