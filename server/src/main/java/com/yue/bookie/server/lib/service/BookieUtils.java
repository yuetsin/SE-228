package com.yue.bookie.server.lib.service;

import com.yue.bookie.server.lib.repository.*;
import com.yue.bookie.server.lib.struct.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
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
    OrderItemRepo orderItemRepo;

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

    public void enableUser(Integer userId) {
        userRepo.enableUser(userId);
    }

    public void disableUser(Integer userId) {
        userRepo.disableUser(userId);
    }

    /* Cart Related Repo Methods */
    public void addToCart(String isbn, Integer count) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userRepo.getUserByName(userDetails.getUsername()).get(0);
        Integer userId = currentUser.id;
        if (cartRepo.findByIsbn(userId, isbn).size() != 0) {
            cartRepo.increaseCart(isbn, userId);
        } else {
            Book addBook = bookRepo.findByIsbn(isbn).get(0);
            System.out.println("userId: " + userId.toString() + " , isbn: " + isbn);
            cartRepo.addToCart(userId, count, isbn);
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

    /* Order Related Repo Methods */
    public List<Order> getOrders() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userRepo.getUserByName(userDetails.getUsername()).get(0);
        Integer userId = currentUser.id;
        return orderRepo.getByUserId(userId);
    }

    /* Mixed Repo Methods */
    public Boolean checkPurchased(String userName, String isbn) {
        try {
            Integer userId = userRepo.getUserByName(userName).get(0).id;
            return bookRepo.checkPurchased(userId, isbn).size() != 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Boolean buyFromCart(String isbn, Integer count, String receiver, String phoneNo, String address) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userRepo.getUserByName(userDetails.getUsername()).get(0);
        Integer userId = currentUser.id;

        Book book = bookRepo.findByIsbn(isbn).get(0);
        if (book.storage >= count) {
            bookRepo.decreaseStorage(isbn, count);
            cartRepo.deleteFromCart(userId, isbn);
            String uuid = UUID.randomUUID().toString();
            orderRepo.addToOrder(uuid, userId, receiver, phoneNo, address);
            orderItemRepo.addToBill(uuid, count, isbn);
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
        service.orderItemRepo = this.orderItemRepo;
        service.cartRepo = this.cartRepo;
        service.orderRepo = this.orderRepo;
        service.userRepo = this.userRepo;
        service.roleRepo = this.roleRepo;
    }

    public void disableBook(@NotNull String isbn) {

        bookRepo.disableBook(isbn);
    }

    public void enableBook(@NotNull String isbn) {
        bookRepo.enableBook(isbn);
    }

    public void setStorage(@NotNull String isbn, int storage) {
        bookRepo.setStorage(isbn, storage);
    }

    @Nullable
    public List<String> getUserRole(@Nullable User user) {
        return userRepo.getUserRole(user.name);
    }

    public void addBook(@NotNull String title, @NotNull String author, @NotNull String type, @NotNull String description, int storage, @NotNull String coverId, String isbn, float priceNum, float couponPriceNum) {
        bookRepo.addNewBook(title, author, type, description, storage, coverId, isbn, priceNum, couponPriceNum);
    }

    @NotNull
    public List<Order> getAllOrders() {
        return orderRepo.getAllOrders();
    }

    @NotNull
    public List<OrderItem> getOrderItems(@Nullable String billUuid) {
        return orderItemRepo.getEveryOrderItemsByUuid(billUuid);
    }

    @Nullable
    public User getUserById(@Nullable Integer userId) {
        List<User> result = userRepo.getUserById(userId);
        if (result.size() > 0) {
            return result.get(0);
        }
        return null;
    }
}
