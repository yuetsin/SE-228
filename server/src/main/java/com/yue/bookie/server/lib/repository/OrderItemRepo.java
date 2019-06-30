package com.yue.bookie.server.lib.repository;


import com.yue.bookie.server.lib.struct.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface OrderItemRepo extends JpaRepository<Book, String> {

    @Query(value = "SELECT * FROM order_items NATURAL JOIN paid_orders lib WHERE isbn = :isbn AND user_id = :userId", nativeQuery = true)
    public List<Book> checkPurchased(@Param("userId") Integer userId, @Param("isbn") String isbn);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO order_items(`bill_uuid`, `count`, `isbn`) VALUES (?1, ?2, ?3)", nativeQuery = true)
    public void addToBill(String uuid, Integer count, String isbn);
}