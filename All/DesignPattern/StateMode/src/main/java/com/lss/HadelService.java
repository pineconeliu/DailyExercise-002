package com.lss;

import java.util.HashMap;

import static com.lss.State.bianji;
import static com.lss.State.tishen;

public class HadelService {

    HashMap<Enum<State>, opreaImpl> map = new HashMap();

    public HadelService() {
        map.put(bianji, new bianjiImpl());
        map.put(tishen, new tishenImpl());
    }

    public void bianji(Enum<State> state) {
        map.get(state).bianji();
    }

    public void tishen(Enum state) {
        map.get(state).tishen();
    }


    public void cheshen(Enum state) {
        map.get(state).cheshen();
    }


    public void pass(Enum state) {
        map.get(state).pass();
    }


    public void refuse(Enum state) {
        map.get(state).refuse();
    }


    public void close(Enum state) {
        map.get(state).close();
    }


    public void open(Enum state) {

    }
}
