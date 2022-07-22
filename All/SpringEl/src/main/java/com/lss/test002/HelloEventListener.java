package com.lss.test002;

import com.google.common.eventbus.Subscribe;
import com.sun.istack.internal.NotNull;
import org.checkerframework.checker.units.qual.Temperature;

public class HelloEventListener {

    @Subscribe
    public void listen(OrderEvent orderEvent){
        System.out.println("receive1 msg:"+ orderEvent.getEventName());
    }

    @Subscribe
    public void listen(String event){
        System.out.println("receive1 msg:"+ event);
    }

}



