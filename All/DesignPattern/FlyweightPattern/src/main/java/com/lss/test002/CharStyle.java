package com.lss.test002;

import java.util.HashMap;

public class CharStyle {
    private String colr;

    private Boolean bold;

    public String getColr() {
        return colr;
    }


    @Override
    public boolean equals(Object obj) {
        CharStyle charStyle = (CharStyle) obj;
        if(this.colr.equals(charStyle.getColr()) &&
           this.bold.equals(charStyle.getBold())  ){
            return true;
        }
        return false;
    }

    public CharStyle(String colr, Boolean bold) {
        this.colr = colr;
        this.bold = bold;
    }

    public void setColr(String colr) {
        this.colr = colr;
    }

    public Boolean getBold() {
        return bold;
    }

    public void setBold(Boolean bold) {
        this.bold = bold;
    }
}
