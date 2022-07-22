package com.lss;

//文字 包括字符，字体颜色，字体是否加粗
public class Charst {

    private char aChar;

    private String colr;

    private Boolean bold;

    public Charst(char aChar, String colr, Boolean bold) {
        this.aChar = aChar;
        this.colr = colr;
        this.bold = bold;
    }

    @Override
    public String toString() {
        return "Charst{" +
                "aChar=" + aChar +
                ", colr='" + colr + '\'' +
                ", bold=" + bold +
                '}';
    }

    public char getaChar() {
        return aChar;
    }

    public void setaChar(char aChar) {
        this.aChar = aChar;
    }

    public String getColr() {
        return colr;
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
