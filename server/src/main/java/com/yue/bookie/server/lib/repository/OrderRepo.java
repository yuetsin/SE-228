package com.yue.bookie.server.lib.repository;

import com.yue.bookie.server.lib.struct.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, String> {
    public List<Order> getByUserId(Integer userId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO orders(`bill_uuid`, `user_id`, `time`, `receiver`, `phone_no`, `address`) VALUES (?1, ?2, NOW(),?3, ?4, ?5)", nativeQuery = true)
    public void addToOrder(String uuid, Integer userId, String receiver, String phoneNo, String address);
}
