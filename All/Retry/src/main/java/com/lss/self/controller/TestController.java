package com.lss.self.controller;

import com.lss.self.annotation.Retry;
import com.lss.self.inter.impl.DefaultRetryListener;
import com.lss.self.inter.impl.FastRetryStrategy;
import com.lss.self.master.UserMapper;
import com.lss.self.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private UserMapper userMapper;

    public static int count = 0;
     
    @Retry(maxAttempts = 5, delay = 100, value = {ArithmeticException.class}, strategy = FastRetryStrategy.class,
            listener = DefaultRetryListener.class,retryIfResult = "success")
    @GetMapping(value = "/do-test")
    @ResponseBody
/*    @Transactional*/
    public String doTest(@RequestParam  int code) {

       /* UserEntity userEntity = new UserEntity();
        userEntity.setUserid("2131");
        userEntity.setUsername("测试");
        userMapper.insert(userEntity);*/

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