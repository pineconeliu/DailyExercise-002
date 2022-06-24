package com.lss.self.inter;

public class SynchronizedExample {
    private  int x = 0;

    public void synBlock(){
        synchronized (SynchronizedExample.class){
            x= 1;
        }
    }

    public synchronized void synMethod(){
        x= 2;
    }
}
