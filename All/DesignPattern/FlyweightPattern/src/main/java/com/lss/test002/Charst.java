package com.lss.test002;

//文字 包括字符，字体颜色，字体是否加粗
public class Charst {

    private char aChar;

    private CharStyle charStyle;

    @Override
    public String toString() {
        return "Charst{" +
                "aChar=" + aChar +
                ", charStyle=" + charStyle +
                '}';
    }

    public char getaChar() {
        return aChar;
    }

    public void setaChar(char aChar) {
        this.aChar = aChar;
    }

    public CharStyle getCharStyle() {
        return charStyle;
    }

    public void setCharStyle(CharStyle charStyle) {
        this.charStyle = charStyle;
    }

    public Charst(char aChar, CharStyle charStyle) {
        this.aChar = aChar;
        this.charStyle = charStyle;
    }
}
