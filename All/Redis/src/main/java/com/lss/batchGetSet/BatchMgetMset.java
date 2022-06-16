package com.lss.batchGetSet;


import redis.clients.jedis.Jedis;

/*
批量操作新增和获取
 */
public class BatchMgetMset {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        Long userId = new Long(001);
        String title_key = "article::"+userId+"::title";
        String content_key = "article::"+userId+"::content";
        String author_key = "article::"+userId+"::author";

        Long aLong = jedis.msetnx(title_key, "学习redis msentx的用法",
                content_key, "msetnx的入参是一个个的键值对，能够实现批量的新增操作，" +
                        "但是如果这些key在这之前存在有值的话就不能新增成功，会返回0",
                author_key ,"lss");

        System.out.println(aLong);
        System.out.println("文章标题是："+jedis.get(title_key));
        System.out.println("文章内容是："+jedis.get(content_key));
        System.out.println("文章作者是："+jedis.get(author_key));
        System.out.println("文章整体信息是："+jedis.mget(title_key,content_key,author_key));
        String mset = jedis.mset(title_key, "学习redis mset的用法",
                content_key, "mset的入参是一个个的键值对，能够实现批量的新增操作，" +
                        "每次执行都是会更新一次操作，成功操作就返回ok字符串",
                author_key, "lss");
        System.out.println(mset);
        System.out.println("文章标题是："+jedis.get(title_key));
        System.out.println("文章内容是："+jedis.get(content_key));
        System.out.println("文章作者是："+jedis.get(author_key));
        System.out.println("文章整体信息是："+jedis.mget(title_key,content_key,author_key));

    }
}
