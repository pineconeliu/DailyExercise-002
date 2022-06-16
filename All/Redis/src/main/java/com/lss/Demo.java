package com.lss;

import redis.clients.jedis.Jedis;

/**
 * @author: 刘松生
 * @Date: 2022/3/13
 * @Version: 1.1
 * @Description:
 */
public class Demo {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        System.out.println(jedis.ping());
        jedis.set("demo", "test");
        System.out.println(jedis.get("demo"));



    }

}
