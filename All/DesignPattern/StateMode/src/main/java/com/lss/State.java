package com.lss;

//

public enum State {

    bianji("bianji"),
    tishen("tishen"),
    cheshen("cheshen"),
    pass("pass"),
    refuse("refuse"),
    close("close"),
    open("open");

    private String name;

    State(String bianji) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
