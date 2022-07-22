package com.lss.test002;

import com.google.common.eventbus.Subscribe;

public class Hello1EventListener {

    @Subscribe
    public void listen(OrderEvent orderEvent){
        System.out.println("Hello1 msg:"+ orderEvent.getEventName());
    }

    @Subscribe
    public void listen1(OrderEvent orderEvent){
        System.out.println("Hello1 msg1:"+ orderEvent.getEventName());
    }
    @Subscribe
    public void listen(String event){
        System.out.println("Hello1 msg:"+ event);
    }

}



