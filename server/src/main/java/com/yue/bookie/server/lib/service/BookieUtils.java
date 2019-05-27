package com.yue.bookie.server.lib.service;

import com.yue.bookie.server.lib.repository.BookRepo;
import com.yue.bookie.server.lib.struct.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class BookieUtils {
    @Autowired
    BookRepo bookRepo;

    public List<Book> ambiguousFind(String keyword) {
        return bookRepo.ambiguousFind(keyword);
    }

    public static BookieUtils service;

    @PostConstruct
    public void init() {
        service = this;
        service.bookRepo = this.bookRepo;
    }
}
