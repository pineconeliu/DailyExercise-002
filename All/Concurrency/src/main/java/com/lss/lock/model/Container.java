package com.lss.lock.model;


import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 10380
 * 生产者消费者模型--容器类
 */
public class Container {

    private final  Lock lock = new ReentrantLock();

    //表示生产者线程 允许操作的条件（容器还没满就允许往里面加数据）
    private final Condition notFull = lock.newCondition();

    //表示消费者线程 允许操作的条件（容器不为空就允许往里面取数据）
    private final Condition notEmpty = lock.newCondition();

    // 有100个库存
    private int stock ;

    //设置一个队列，每次达到队列满的时候才提交操作
    private final ArrayBlockingQueue blockingDeque =  new ArrayBlockingQueue<Integer>(10);

    public Container(int stock) {
        this.stock = stock;
    }

    //往容器里加数据
    public  void   add(){
        lock.lock();
        try {
            while (true) {
                 //这个数字1 可以看成是一个用户对象来请求，里面会包含用户的购买数量
                if (!blockingDeque.offer(1)) {
                    System.out.println("系统繁忙，新增队列失败！");
                    //阻塞生产者线程
                    notFull.await();
                }
                //可以加数据就一直加
                System.out.println("生产者加数据");
                notEmpty.signalAll();
            }

        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }

    }

    public void take(){
        lock.lock();
        try {
            while (true){
                System.out.println("进入消费者");
                //如果队列头部没有值
                if(Objects.isNull(blockingDeque.peek())){
                    try {
                        notEmpty.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                try {
                    blockingDeque.poll();
                    stock -=1;
                    System.out.println("消费者消费数据");
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }
        finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Container container = new Container(100);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                container.take();
            }
        };

        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                container.add();
            }
        };
        Thread thread = new Thread(runnable);
        Thread thread1 = new Thread(runnable1);
        thread.start();
        thread1.start();

    }
}
