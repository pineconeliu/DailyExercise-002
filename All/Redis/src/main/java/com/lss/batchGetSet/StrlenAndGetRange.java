package com.lss.batchGetSet;

import redis.clients.jedis.Jedis;

/*
 返回文章的长度和文章预览（获取指定的数据）
 */
public class StrlenAndGetRange {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        Long userId = new Long(001);
        String title_key = "article::"+userId+"::title";
        String content_key = "article::"+userId+"::content";
        String author_key = "article::"+userId+"::author";

        System.out.println("文章内容的长度是："+jedis.strlen(content_key));

        System.out.println("文章预览一部分数据："+jedis.getrange(content_key,0,12));

    }
}
