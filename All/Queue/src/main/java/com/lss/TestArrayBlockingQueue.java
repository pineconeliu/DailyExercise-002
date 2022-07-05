package com.lss;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * ArrayBlockingQueue
 * ArrayBlockingQueue，一个由数组实现的有界阻塞队列。该队列采用先进先出（FIFO）的原则对元素进行排序添加的。
 * ArrayBlockingQueue 为有界且固定，其大小在构造时由构造函数来决定，确认之后就不能再改变了。
 * ArrayBlockingQueue 支持对等待的生产者线程和使用者线程进行排序的可选公平策略，但是在默认情况下不保证线程公平的访问，
 * 在构造时可以选择公平策略（fair = true）。公平性通常会降低吞吐量，但是减少了可变性和避免了“不平衡性”。
 * （ArrayBlockingQueue 内部的阻塞队列是通过 ReentrantLock 和 Condition 条件队列实现的， 所以 ArrayBlockingQueue中的元素存在公平和非公平访问的区别）
 * 所谓公平访问队列是指阻塞的所有生产者线程或消费者线程，当队列可用时，可以按照阻塞的先后顺序访问队列，即先阻塞的生产者线程，
 * 可以先往队列里插入元素，先阻塞的消费者线程，可以先从队列里获取元素，可以保证先进先出，避免饥饿现象。
 */
public class TestArrayBlockingQueue {

    final  static BlockingQueue<String> blockingQueue = new ArrayBlockingQueue(2);

    @SuppressWarnings(value = "unchecked")
    //异常组-操作有 add(),remove(),element()
    public static void errorGroup(){
        System.out.println("新增一个元素："+blockingQueue.add("001")); //true
        System.out.println("新增一个元素："+blockingQueue.add("002")); //true

        /*System.out.println("新增一个元素容量超出大小："+blockingQueue.add("003"));*/ //报错，queue full

        //检索但不删除此队列的头。此方法与peek的不同之处在于，如果此队列为空，它会引发异常
        System.out.println("获取队列头部的元素："+blockingQueue.element()); //001

        System.out.println("获取队列头部的元素："+blockingQueue.element()); //001

        //从该队列中删除指定元素的单个实例（如果存在）。更正式地说，如果此队列包含一个或多个这样的元素，则删除元素e，
        // 使o.equals（e）。如果此队列包含指定的元素，则返回true（如果此队列因调用而更改，则返回等效值）。
        // 参数: o–要从此队列中删除的元素（如果存在） 返回值: 如果此队列因调用而更改，则为true
        System.out.println("移除一个已经存在的数据:"+blockingQueue.remove("001")); //移除一个已经存在的数据
        System.out.println("移除一个不存在的数据，并且队列还不为空:"+blockingQueue.remove("004")); //移除一个不存在的数据，并且队列还不为空
        System.out.println("移除队列最后一个元素:"+blockingQueue.remove("002")); //移除队列最后一个元素

        /*System.out.println("队列为空的时候，获取队列头部的元素："+blockingQueue.element());*/ //抛出 NoSuchElementException 异常

        System.out.println("队列为空时获取队列头部的元素："+blockingQueue.element()); // NoSuchElementException

        System.out.println("队列为空时操作remove(\"003\"):"+blockingQueue.remove("003")); // false

        //检索并删除此队列的头。此方法与轮询的不同之处在于，如果此队列为空，则会引发异常
        System.out.println("队列为空时操作remove():"+blockingQueue.remove()); // NoSuchElementException
    }

    @SuppressWarnings(value = "unchecked")
    //返回特殊值-操作有 offer(),peek(),poll()
    public static void specialGroup(){
        //如果可以在不违反容量限制的情况下立即将指定元素插入此队列，则在成功时返回true，如果当前没有可用空间，则返回false。
        System.out.println("新增一个元素："+blockingQueue.offer("001")); //true
        System.out.println("新增一个元素："+blockingQueue.offer("002")); //true
        System.out.println("新增一个元素容量超出大小："+blockingQueue.offer("003")); // false

        //检索但不删除此队列的头。此方法与peek的不同之处在于，如果此队列为空，它会引发异常
        System.out.println("获取队列头部的元素："+blockingQueue.peek()); //001

        System.out.println("获取队列头部的元素："+blockingQueue.peek()); //001

        //检索并删除此队列的头，如果此队列为空，则返回null。
        System.out.println("移除队列头部的元素:"+blockingQueue.poll()); //001
        System.out.println("移除队列头部的元素:"+blockingQueue.poll()); //002
        System.out.println("队列为空时移除队列头部的元素:"+blockingQueue.poll()); //null

    }
    //一直阻塞：
    //当阻塞队列满时，如果生产线程继续往队列里 put 元素，队列会一直阻塞生产线程，直到拿到数据，或者响应中断退出；
    //当阻塞队列空时，消费线程试图从队列里 take 元素，队列也会一直阻塞消费线程，直到队列可用。
    public static void blockGroup(){
        try {

            blockingQueue.put("001");
            blockingQueue.put("002");
            System.out.println("===============");
            /*blockingQueue.put("003");*/ // 一直阻塞

            System.out.println(blockingQueue.take()); //001
            System.out.println(blockingQueue.take()); //002

           /* System.out.println(blockingQueue.take()); *///阻塞，一直等待
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //超时退出：
    //当阻塞队列满时，队列会阻塞生产线程一定时间，如果超过一定的时间，生产线程就会退出，返回 false
    //当阻塞队列空时，队列会阻塞消费线程一定时间，如果超过一定的时间，消费线程会退出，返回 null
    public static void blockOverTimeGroup(){
        try {

            blockingQueue.offer("001",2, TimeUnit.MICROSECONDS);
            blockingQueue.offer("002",2, TimeUnit.MICROSECONDS);
            blockingQueue.offer("002",2, TimeUnit.MICROSECONDS); //2秒内塞值，塞不进去直接false

            System.out.println("===============");
            System.out.println(blockingQueue.poll(2, TimeUnit.MICROSECONDS)); //001
            System.out.println(blockingQueue.poll(2, TimeUnit.MICROSECONDS));  //002
            System.out.println(blockingQueue.poll(2, TimeUnit.MICROSECONDS));  //2秒内获取不到值，返回null

            /* System.out.println(blockingQueue.take()); *///阻塞，一直等待
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
       /* errorGroup();*/

       /* specialGroup();*/

        /*blockGroup();*/
        blockOverTimeGroup();
    }
}
