package com.yue.bookie.server.lib.repository;

import com.yue.bookie.server.lib.struct.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book, String> {
    public List<Book> findByTitleContaining(String title);

    public List<Book> findByAuthorContaining(String author);

    @Query(value = "SELECT * FROM book_library lib WHERE disabled = FALSE AND (locate(:keyword, lib.title) > 0 OR locate(:keyword, lib.author) > 0)", nativeQuery = true)
    public List<Book> ambiguousFind(@Param("keyword") String keyword);

    public List<Book> findByType(String type);

    public List<Book> findByIsbn(String isbn);

    @Modifying
    @Transactional
    @Query(value = "UPDATE book_library SET `storage` = `storage` - ?2 WHERE isbn = ?1", nativeQuery = true)
    public void decreaseStorage(String isbn, Integer storage);

    @Modifying
    @Transactional
    @Query(value = "UPDATE book_library SET `storage` = ?2 WHERE isbn = ?1", nativeQuery = true)
    public void setStorage(String isbn, Integer setStorage);

    @Modifying
    @Transactional
    @Query(value = "UPDATE book_library SET `disabled` = FALSE WHERE `isbn` = ?1", nativeQuery = true)
    public void enableBook(String isbn);

    @Modifying
    @Transactional
    @Query(value = "UPDATE book_library SET `disabled` = TRUE WHERE `isbn` = ?1", nativeQuery = true)
    public void disableBook(String isbn);


    @Modifying
    @Transactional
    @Query(value = "INSERT INTO book_library VALUES (FALSE, ?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9)", nativeQuery = true)
    void addNewBook(String title, String author, String type, String description, int storage, String coverId, String isbn, float priceNum, float couponPriceNum);
}
