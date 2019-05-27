package com.yue.bookie.server.lib.controller;

import com.yue.bookie.server.lib.config.BehaviorConfig;
import com.yue.bookie.server.lib.service.BookieUtils;
import com.yue.bookie.server.lib.struct.Book;
import org.json.JSONArray;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

@RestController
public class BookQueryController {
    @RequestMapping(value = "/search", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @CrossOrigin
    @ResponseBody
    public String api(String q) {
        if (q.length() < 1) {
            return "{\"status\": \"bad_request\"}";
        }
        System.out.println("Gotta request " + q);
        if (!BehaviorConfig.useLegacyJson) {
            try {
                List<Book> books = BookieUtils.service.ambiguousFind(q);
                JSONArray JSONArray = new JSONArray(books);
                System.out.println(JSONArray.toString());
                return String.format("{\"status\": \"ok\", \"data\": %s}", JSONArray.toString());
            } catch (Exception ex) {
                ex.printStackTrace();
                return "{\"status\": \"internal_error\"}";
            }
        } else {
            // Keep legacy solution in case if you need it...
            File file02 = new File("assets/books/books.json");
            FileInputStream is;
            StringBuilder stringBuilder = null;
            try {
                if (file02.length() != 0) {
                    /**
                     * 文件有内容才去读文件
                     */
                    is = new FileInputStream(file02);
                    InputStreamReader streamReader = new InputStreamReader(is);
                    BufferedReader reader = new BufferedReader(streamReader);
                    String line;
                    stringBuilder = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        // stringBuilder.append(line);
                        stringBuilder.append(line);
                    }
                    reader.close();
                    is.close();
                } else {
                    return "{\"status\": \"json_error\"}";
                }

            } catch (Exception e) {
                e.printStackTrace();
                return "{\"status\": \"internal_error\"}";
            }
            return String.valueOf(stringBuilder);
        }
    }
}
