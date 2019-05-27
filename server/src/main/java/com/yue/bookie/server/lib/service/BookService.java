package com.yue.bookie.server.lib.service;

import com.yue.bookie.server.lib.repository.BookRepo;
import com.yue.bookie.server.lib.repository.CommentRepo;
import com.yue.bookie.server.lib.struct.Book;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BookService {
    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private CommentRepo commentRepo;

    public List<Object[]> findAllBooks(String keyword)
    {
        return bookRepo.ambiguousFind(keyword);
    }
}
