package com.yue.bookie.server.lib.service;

import com.yue.bookie.server.lib.repository.*;
import com.yue.bookie.server.lib.struct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.ResultSet;
import java.util.List;
import java.util.UUID;

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

    /* Cart Related Repo Methods */
    public void addToCart(String isbn, Integer count) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userRepo.getUserByName(userDetails.getUsername()).get(0);
        Integer userId = currentUser.id;
        if (cartRepo.findByIsbn(isbn).size() != 0) {
            cartRepo.increaseCart(isbn, userId);
        } else {
            Book addBook = bookRepo.findByIsbn(isbn).get(0);
            cartRepo.addToCart(userId, count, isbn, addBook.getTitle(), addBook.getAuthor());
        }
    }

    public void deleteFromCart(String isbn) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userRepo.getUserByName(userDetails.getUsername()).get(0);
        Integer userId = currentUser.id;
        System.out.println(String.format("id = %d, isbn = %s", userId, isbn));
        cartRepo.deleteFromCart(userId, isbn);
    }

    public List<Cart> getUserCart() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userRepo.getUserByName(userDetails.getUsername()).get(0);
        Integer userId = currentUser.id;

        List<Cart> resultSet = cartRepo.findByUserId(userId);
//        System.out.println(resultSet.get(0).title);
        return resultSet;
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

    public Boolean buyFromCart(String isbn, Integer count) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userRepo.getUserByName(userDetails.getUsername()).get(0);
        Integer userId = currentUser.id;

        Book book = bookRepo.findByIsbn(isbn).get(0);
        if (book.storage >= count) {
            bookRepo.decreaseStorage(isbn, count);
            cartRepo.deleteFromCart(userId, isbn);
            String uuid = UUID.randomUUID().toString();
            billRepo.addToBill(uuid, count, isbn);
            return true;
        } else {
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
