package com.yue.bookie.server.lib.repository;

import com.yue.bookie.server.lib.struct.Book;
import com.yue.bookie.server.lib.struct.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    public List<User> getUserByName(String name);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO s_user(`'name'`, `password`) VALUES (?1, ?2) INSERT INTO s_role(`'id'`, `'role'`) VALUES (LAST_INSERT_ID(), 'R_USER'); ", nativeQuery = true)
    void registerNewUser(String name, String password);
}
