package com.yue.bookie.server.lib.service;

import com.yue.bookie.server.lib.repository.*;
import com.yue.bookie.server.lib.struct.Book;
import com.yue.bookie.server.lib.struct.Comment;
import com.yue.bookie.server.lib.struct.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class BookieUtils {
    @Autowired
    BookRepo bookRepo;

    @Autowired
    CommentRepo commentRepo;

    @Autowired
    BillRepo billRepo;

    @Autowired
    CartRepo cartRepo;

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    UserRepo userRepo;

    public List<Book> ambiguousFind(String keyword) {
        return bookRepo.ambiguousFind(keyword);
    }

    public List<Comment> getCommentByIsbn(String isbn) {
        return commentRepo.findAllByIsbn(isbn);
    }

    public List<Comment> getCommentByUserId(Integer userId) {
        return commentRepo.findAllByUserId(userId);
    }

    public void addComments(String isbn, String contents) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userRepo.getUserByName(userDetails.getUsername());
        Integer userId = currentUser.id;
        Boolean purchased = checkPurchased(currentUser.name, isbn);
        commentRepo.addComments(userId, purchased, isbn, contents);
    }

    public Boolean checkPurchased(String userName, String isbn) {
        Integer userId = userRepo.getUserByName(userName).id;
        return billRepo.checkPurchased(userId, isbn).size() != 0;
    }

    public List<Book> getBookByIsbn(String isbn) {
        return bookRepo.findByIsbn(isbn);
    }

    public static BookieUtils service;

    @PostConstruct
    public void init() {
        service = this;
        service.bookRepo = this.bookRepo;
        service.commentRepo = this.commentRepo;
        service.billRepo = this.billRepo;
        service.cartRepo = this.cartRepo;
        service.orderRepo = this.orderRepo;
        service.userRepo = this.userRepo;
    }
}
