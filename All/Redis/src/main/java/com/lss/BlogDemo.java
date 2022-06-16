package com.lss;

import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @author: 刘松生
 * @Date: 2022/3/13
 * @Version: 1.1
 * @Description: 用redis 可以实现缓存博客的信息，比如，博客有标题、内容、作者、发布时间组成
 * 这种多个key 的情况进行保存在redis中
 */
public class BlogDemo {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);

        //mset,mget,msetnx,m ->multi 的意思，mest 一下子设置多个key-value对，
        //mget表示一次性获取多个key的value,msetnt就是在多个key不存在的时候一下子设置多个key-value对，

        //当所有 key 都成功设置时，返回 1，如果其中至少一个 key 已经存在，那么将设置失败，此时会返回 0。操作也不会更新
        Long msetnx = jedis.msetnx("article:2:title", "学习redis的msetnx",
                "article:2:content", "具体介绍去如何使用",
                "article:2:author", "lss");
        System.out.println("发布博客信息："+msetnx);

        List<String> mget = jedis.mget("article:2:title", "article:2:content", "article:2:author");
        System.out.println("博客信息："+mget);

        // 修改博客信息BlogDemo
        String mset = jedis.mset("article:2:title", "修改博客信息学习redis的msetnx",
                "article:2:content", "修改博客信息具体介绍去如何使用",
                "article:2:author", "修改博客信息lss");

        System.out.println("修改博客信息："+mset);

        mget = jedis.mget("article:2:title", "article:2:content", "article:2:author");
        System.out.println("博客信息："+mget);

        //因为没有设置失效时间，用这个可以删除key
        jedis.del("article:2:title", "article:2:content", "article:2:author");
        //博客内容的长度统计，中文会
        Long strlen = jedis.strlen("article:2:content");
        System.out.println("博客内容的长度统计："+strlen);
        //截取博客内容的一部分
        String getrange = jedis.getrange("article:2:content",0,5);
        System.out.println("截取博客内容的一部分："+getrange);


    }
}
