package com.yue.bookie.server.lib.repository;

import com.yue.bookie.server.lib.struct.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book, String> {
    public List<Book> findByTitleContaining(String title);

    public List<Book> findByAuthorContaining(String author);

    @Query(value = "SELECT * FROM book_library lib WHERE locate(:keyword, lib.title) >= 0 OR locate(:keyword, lib.author) >= 0", nativeQuery = true)
    public List<Book> ambiguousFind(@Param("keyword") String keyword);

    public List<Book> findByType(String type);

    public Object findByIsbn(String isbn);
}
