package com.yue.bookie.server.lib.service;

import com.yue.bookie.server.lib.repository.*;
import com.yue.bookie.server.lib.struct.Book;
import com.yue.bookie.server.lib.struct.Comment;
import com.yue.bookie.server.lib.struct.Role;
import com.yue.bookie.server.lib.struct.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class BookieUtils {

    /* Repo Instance Autowire */

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

    @Autowired
    RoleRepo roleRepo;

    /* Book Related Repo Methods */
    public List<Book> ambiguousFind(String keyword) {
        return bookRepo.ambiguousFind(keyword);
    }

    public List<Book> getBookByIsbn(String isbn) {
        return bookRepo.findByIsbn(isbn);
    }

    /* Comments Related Repo Methods */
    public List<Comment> getCommentByIsbn(String isbn) {
        return commentRepo.findAllByIsbn(isbn);
    }

    public List<Comment> getCommentByUserId(Integer userId) {
        return commentRepo.findAllByUserId(userId);
    }

    public void addComments(String isbn, String contents) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userRepo.getUserByName(userDetails.getUsername()).get(0);
        Integer userId = currentUser.id;
        Boolean purchased = checkPurchased(currentUser.name, isbn);
        commentRepo.addComments(userId, purchased, isbn, contents);
    }

    /* User Related Repo Methods */
    public List<User> getUserByName(String name) { return userRepo.getUserByName(name); }

    public void registerUser(String username, String password) {
        userRepo.registerNewUser(username, password);
        roleRepo.setRole();
    }

    /* Mixed Repo Methods */
    public Boolean checkPurchased(String userName, String isbn) {
        try {
            Integer userId = userRepo.getUserByName(userName).get(0).id;
            return billRepo.checkPurchased(userId, isbn).size() != 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }


    /* Initialize Service */
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
        service.roleRepo = this.roleRepo;
    }
}
