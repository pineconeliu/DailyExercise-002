package com.lss.controller;

import com.lss.annotation.Retry;
import com.lss.inter.impl.DefaultRetryListener;
import com.lss.inter.impl.FastRetryStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
 
    public static int count = 0;
     
    @Retry(maxAttempts = 5, delay = 100, value = {ArithmeticException.class}, strategy = FastRetryStrategy.class, listener = DefaultRetryListener.class)
    @GetMapping(value = "/do-test")
    @ResponseBody
    public String doTest(@RequestParam  int code) {
        //count值一直再新增
        count++;
        System.out.println("code is :" + code + " result is :" + count % 3 + " count is :" + count);
        if (code == 1) {
            System.out.println("--this is a test");
        } else {
            if (count % 5 != 0) {
                System.out.println(4 / 0);
            }
        }
        return "success";
    }
}