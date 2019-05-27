package com.yue.bookie.server.lib.repository;

import com.yue.bookie.server.lib.struct.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends JpaRepository<Book, String> {

}
