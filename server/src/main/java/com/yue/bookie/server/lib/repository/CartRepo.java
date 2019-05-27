package com.yue.bookie.server.lib.repository;

import com.yue.bookie.server.lib.struct.Book;
import com.yue.bookie.server.lib.struct.Cart;
import com.yue.bookie.server.lib.struct.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sun.security.x509.SerialNumber;

import javax.transaction.Transactional;
import java.lang.invoke.SerializedLambda;
import java.util.List;

@Repository
public interface CartRepo extends JpaRepository<Cart, String> {
    @Query(value = "SELECT user_id, `count`, isbn, title, author FROM carts STRAIGHT_JOIN book_library ON carts.isbn = book_library.isbn WHERE user_id = ?1;", nativeQuery = true)
    public List<Cart> findAllByUserId(Integer userId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO carts(`user_id`, `count`, `isbn`) VALUES (?1, ?2, ?3);", nativeQuery = true)
    public void addToCart(Integer userId, Integer count, String isbn);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM carts WHERE isbn = ?1 AND user_id = ?2", nativeQuery = true)
    public void deleteFromCart(Integer userId, String isbn);
}
