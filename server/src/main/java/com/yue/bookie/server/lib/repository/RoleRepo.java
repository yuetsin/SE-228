package com.yue.bookie.server.lib.repository;

import com.yue.bookie.server.lib.struct.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO s_user_role VALUES (LAST_INSERT_ID(), 2)", nativeQuery = true)
    void setRole();
}
