package com.yue.bookie.server.controller;

import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

@RestController
public class BookQueryController {
    @RequestMapping(value = "/api", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String api(){
        File file02 = new File("assets/books/books.json");
        FileInputStream is = null;
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
                return "{}";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(stringBuilder);
    }
}
