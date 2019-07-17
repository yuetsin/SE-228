package com.yue.bookie.server.lib.controller;
import com.yue.bookie.server.lib.service.BookieUtils;

import com.yue.bookie.server.lib.struct.Book;
import com.yue.bookie.server.lib.struct.Cart;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Vector;

@RestController
public class CheckMarketController {
    @RequestMapping(value = "/market", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String market() {
        try {
            List<Cart> currentUserCart = BookieUtils.service.getUserCart();

            String builtStr = "";
            List<String> partial = new Vector<>();
            for (Cart cart : currentUserCart) {
                List<Book> foundBooks = BookieUtils.service.getBookByIsbn(cart.isbn);
                if (foundBooks.size() != 0) {
                    partial.add(String.format("{\"isbn\": \"%s\", \"count\": %d, \"author\": \"%s\", \"title\": \"%s\", \"price\": %.2f}", cart.isbn, cart.count, foundBooks.get(0).author, foundBooks.get(0).title, foundBooks.get(0).couponPrice));
                }
            }
            System.out.println(builtStr);
            return String.format("{\"status\": \"ok\", \"data\": [%s]}",String.join(", ", partial));
        } catch (Exception ex) {
            ex.printStackTrace();
            return "{\"status\": \"internal_error\"}";
        }
    }
}