package com.lss.hash;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: 刘松生
 * @Date: 2022/3/13
 * @Version: 1.1
 * @Description: 用redis 可以实现缓存博客的信息，比如，博客有标题、内容、作者、发布时间组成
 * 这种多个key 的情况进行保存在redis中
 */
public class BlogDemo {
    //hash 操作类似于数据库中的表字段操作，先获取表，再根据字段去获取数值
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        Map<String, String> hash = new HashMap<>();
        hash.put("article:2:title", "博客信息学习redis的msetnx");
        hash.put("article:2:content", "博客信息具体介绍去如何使用");
        hash.put("article:2:author", "博客信息lss");
        jedis.hmset("Blog::Lss",hash);
        Map<String, String> stringStringMap = jedis.hgetAll("Blog::Lss");
        stringStringMap.forEach((key,value)-> System.out.println(key+" "+value));
        //判断hash结构中某个字段是否存在
        Boolean hexists = jedis.hexists("Blog::Lss", "article:2:author");
        System.out.println("判断hash结构中某个字段是否存在："+hexists);
        //修改字段的值内容，如果字段存在就直接更新字段的值，如果不存在就新增该字段和值
        jedis.hset("Blog::Lss","article:2:title", "修改博客信息学习redis的msetnx");
        System.out.println(jedis.hget("Blog::Lss","article:2:title"));
        jedis.hset("Blog::Lss","article:2:title2112", "新增的博客信息学习redis的msetnx");
        System.out.println(jedis.hget("Blog::Lss","article:2:title2112"));
        Long hlen = jedis.hlen("Blog::Lss");
        System.out.println("返回散列中的项数（字段数）："+hlen);
        //如果该字段已存在，则返回0，否则，如果创建了新字段，则返回1
        Long hsetnx = jedis.hsetnx("Blog::Lss", "article:2:hsetnx1", "测试不存在的字段，还能新增吗");
        System.out.println("测试不存在的字段，还能新增吗： "+hsetnx);
        //设置一个字段的值为整数型字符串 ，用hincrBy 相加的数也只能是整数不能是浮点数
        jedis.hset("Blog::Lss","article:2:count", "20");
        jedis.hincrBy("Blog::Lss","article:2:count",12);
        System.out.println("整数型字符串相加之和的值是："+jedis.hget("Blog::Lss","article:2:count"));
        //hincrByFloat 加的值可以是整数也可以是浮点数
        jedis.hincrByFloat("Blog::Lss","article:2:count",11.2);
        System.out.println("浮点数相加之和的值是："+jedis.hget("Blog::Lss","article:2:count"));
        //获取这个key下所有的域（这个表下所有的字段名有哪些）
        Set<String> hkeys = jedis.hkeys("Blog::Lss");
        System.out.println("这个表下所有的字段名有哪些："+ hkeys);
        //获取这个key下所有的域的值（这个表下所有的字段名下的所有值内容）
        List<String> hvals = jedis.hvals("Blog::Lss");
        System.out.println("这个表下所有的字段名下的所有值内容："+ hvals);
        //获取这个key下所有的域（这个表下所有的字段名有哪些）
        ScanResult<Map.Entry<String, String>> hscan = jedis.hscan("Blog::Lss", "-1");
        System.out.println("这个表下所有的字段名和value：");
        hscan.getResult().stream().forEach(System.out::println);
        //删除表下面的字段，如果字段是不存在的返回0,如果有多个参数就一起删除，每成功删除一个key影响的数量加1
        Long hdel = jedis.hdel("Blog::Lss","article:2:hsetnx1212","article:2:count");
        System.out.println("删除表所影响的数量："+hdel);
        //无法直接删除这个key（表）会报错
       try {
           Long hdel1 = jedis.hdel("Blog::Lss");
       }catch (Exception e){
           System.out.println("无法直接删除这个key（表）会报错：" +  e.getMessage());
       }
       //如果想删除这个key可以使用下面这个方法
        Long del = jedis.del("Blog::Lss");
        System.out.println("删除key:"+del);
        Set<String> keys = jedis.keys("Blog::Lss");
        System.out.println(keys);
        Boolean hexists1 = jedis.hexists("Blog::Lss", "article:2:author");
        System.out.println("判断hash结构中某个字段是否存在："+hexists1);
        //这个是针对时间戳的过期时间设置
        jedis.expireAt("Blog::Lss",12321L);
    }
}
