package com.yue.bookie.server.lib.repository;


import com.yue.bookie.server.lib.struct.Book;
import com.yue.bookie.server.lib.struct.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface OrderItemRepo extends JpaRepository<OrderItem, String> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO order_items(`bill_uuid`, `count`, `isbn`) VALUES (?1, ?2, ?3)", nativeQuery = true)
    public void addToBill(String uuid, Integer count, String isbn);

    @Query(value = "SELECT * FROM order_items WHERE `bill_uuid` = ?1", nativeQuery = true)
    public List<OrderItem> getEveryOrderItemsByUuid(String billUuid);
}