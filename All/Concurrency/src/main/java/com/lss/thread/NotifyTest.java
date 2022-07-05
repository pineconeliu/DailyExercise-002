package com.lss.thread;


import lombok.extern.slf4j.Slf4j;
@SuppressWarnings("unchecked")

@Slf4j
public class NotifyTest {

    private static volatile Object resourceA = new Object();


    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        Thread threadA = new Thread(() -> {
            synchronized (resourceA) {
                log.info("Thread A start");
                try {
                    resourceA.wait();
                    log.info("Thread A end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        Thread threadB = new Thread(() -> {
            synchronized (resourceA) {
                log.info("ThreadB start");
                try {
                    resourceA.wait();
                    log.info("ThreadB  end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        Thread threadC = new Thread(() -> {
            synchronized (resourceA) {
                log.info("ThreadC start notify");
                // IF notify ONLY FOR ONE THREAD
                resourceA.notifyAll();
            }
        });



        threadA.start();
        threadB.start();

        Thread.sleep(1000);

        threadC.start();

        threadA.join();
        threadB.join();
        threadC.join();

        log.info("main check");
    }


}
