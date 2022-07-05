package com.lss.thread;

import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SimpleBlockingQueue<T> {

    final Lock lock = new ReentrantLock();

    //
     final Condition notFull =  lock.newCondition();

     //
     final Condition notEmpty =  lock.newCondition();


     final Queue queue = new ArrayBlockingQueue(2);


     //
    public void setInfo(){
        lock.lock();
        try{
            while (!queue.offer(";sss")){
                notFull.await();
            }
            queue.add("lss");

            notEmpty.signal();
        }catch (Exception e){

        }finally {
            lock.unlock();
        }
    }

    public void getInfo(){
        lock.lock();
        try{
            while (Objects.isNull(queue.peek())){
                notEmpty.await();
            }
            queue.remove();
            notFull.signal();
        }catch (Exception e){

        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        SimpleBlockingQueue simpleBlockingQueue = new SimpleBlockingQueue();
        for(int i=0;i<5;i++){
            simpleBlockingQueue.setInfo();
            simpleBlockingQueue.getInfo();
        }
    }

}
