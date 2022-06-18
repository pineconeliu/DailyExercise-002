package com.lss.lock.model;

import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 10380
 * 生产者消费者模型--容器类
 */
public class Container1 {

    private final  Lock lock = new ReentrantLock();

    //表示生产者线程 允许操作的条件（容器还没满就允许往里面加数据）
    private final Condition notFull = lock.newCondition();

    //表示消费者线程 允许操作的条件（容器不为空就允许往里面取数据）
    private final Condition notEmpty = lock.newCondition();

    // 有100个库存
   private    volatile AtomicInteger stock = new AtomicInteger(10);
//    private  static volatile Integer stock = 10;

    //设置一个队列，每次达到队列满的时候才提交操作
    private final ArrayBlockingQueue blockingDeque =  new ArrayBlockingQueue<Integer>(3);

    // 用线程池的空闲时间让等待的线程自动结束？？

    //往容器里加数据
    public  void   add(Integer integer){
        lock.lock();
        try {
            //  如果新增到队列的大小，或者说队列无法再新增了就await呀，同时也要去唤醒消费者。
            //如果新增成功就完事，
            //新增失败就说明达到最大空间
            boolean offer = blockingDeque.offer(integer);
            //如果库存只有2个，队列长度为3只能新增2个
            if( stock.get() == 0 ||  !offer){
                System.out.println(Thread.currentThread().getName()+"系统繁忙，新增队列失败！");
                notEmpty.signal();
                try {
                    notFull.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                //可以加数据就一直加
                System.out.println(Thread.currentThread().getName()+"生产者加"+integer+"数据成功");
            }

        }
        finally {
            lock.unlock();
        }

    }

    public void take(){
        lock.lock();
        try {
            //如果队列头部没有值
            if(stock.get()<3){
                for (int i=0 ; i < stock.get() ;i++){
                    if(stock.get()  < 0){
                        System.out.println(Thread.currentThread().getName() + "stock 库存已消费完成，无法进行生产和消息\"");
                        try {
                            notEmpty.await();
                            notFull.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            if(Objects.isNull(blockingDeque.poll())){
                try {
                    if(stock.get()  == 10){
                        System.out.println(Thread.currentThread().getName()+"消费者等待生产者生成消息");
                    }
                    notFull.signal();
                    System.out.println(Thread.currentThread().getName() + "没有数据可以消费");
                    try {
                        notEmpty.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
          stock.decrementAndGet();
                System.out.println(Thread.currentThread().getName()+"成功消费数据");
            }
        }
        finally {
            if(stock.get()  == 0){
                System.out.println(Thread.currentThread().getName() + "stock 库存已消费完成，无法进行生产和消息\"");
                try {
                    notEmpty.await();
                    notFull.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            lock.unlock();

        }
    }

}
