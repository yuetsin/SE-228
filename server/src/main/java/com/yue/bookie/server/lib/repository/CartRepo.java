package com.yue.bookie.server.lib.repository;

import com.yue.bookie.server.lib.struct.Book;
import com.yue.bookie.server.lib.struct.Cart;
import com.yue.bookie.server.lib.struct.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CartRepo extends JpaRepository<Cart, String> {

    public List<Cart> findAllByUserId(Integer userId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO carts(`user_id`, `count`, `isbn`) VALUES (?1, ?2, ?3);", nativeQuery = true)
    public void addToCart(Integer userId, Integer count, String isbn);
}
