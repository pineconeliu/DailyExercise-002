package com.lss;

import com.lss.entiy.AwlFaceShape;
import com.lss.entiy.NewSummerCloth;
import com.lss.entiy.SlimLongLegBody;
import com.lss.personal.BuilderPerson;
import org.junit.Test;

public class ApiTest {

    @Test
    public void test_Builder(){
        BuilderPerson person = new BuilderPerson("张三").getBody(new SlimLongLegBody())
                .getCloth(new NewSummerCloth())
                .getFaceShape(new AwlFaceShape());
        System.out.println(person.toString());
    }
}
