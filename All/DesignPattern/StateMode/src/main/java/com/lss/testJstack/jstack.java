package com.lss.testJstack;

import sun.nio.ch.ThreadPool;

import java.util.concurrent.*;

public class jstack {

    public static int coreThread = Runtime.getRuntime().availableProcessors();
    public static int maxThread = coreThread * 2;
    public static Object lock = new Object();
    public static ExecutorService threadPool = new ThreadPoolExecutor(coreThread, maxThread, 1, TimeUnit.MINUTES, new LinkedBlockingDeque<>());

    public static void main(String[] args) {
        Task task = new Task();
        Task task1 = new Task();
        threadPool.execute(task);
        threadPool.execute(task1);
    }

    public static class Task implements Runnable {

        @Override
        public void run() {
            synchronized (lock) {
                cal();
            }
        }

        public void cal() {
            int i = 0;
            while (true) {
                i++;
            }
        }
    }
}
