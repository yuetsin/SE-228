package com.yue.bookie.server.lib.repository;

import com.yue.bookie.server.lib.struct.Book;
import com.yue.bookie.server.lib.struct.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    public List<User> getUserByName(String name);

    @Modifying
    @Transactional
    @Query(value = "UPDATE s_user SET `available` = TRUE WHERE `id` = ?1", nativeQuery = true)
    public void enableUser(Integer userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE s_user SET `available` = FALSE WHERE `id` = ?1", nativeQuery = true)
    public void disableUser(Integer userId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO s_user(`name`, `password`, `available`) VALUES (?1, ?2, TRUE)", nativeQuery = true)
    void registerNewUser(String name, String password);
}
