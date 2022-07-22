package com.lss.test002;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CharStyleFactory {

    private static List<CharStyle>  charStyleList = new ArrayList();

    public static CharStyle repeat(CharStyle charStyle){
        for (CharStyle charStylec : charStyleList) {
            if(charStyle.equals(charStylec)){
                return charStylec;
            }
        }
        charStyleList.add(charStyle);
        return charStyle;

    }
}
