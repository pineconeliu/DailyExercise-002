package com.lss;

import org.junit.Test;

public class ApiTest {

    @Test
    public void test_QuestionBank() throws CloneNotSupportedException {
        //便于通过克隆⽅式创建复杂对象、也可以避免᯿复做初始化操作、不需要与类中所属的其他类耦合等
        //共用同一个类，数据在对象实例化的时候就执行了
        QuestionBankController questionBankController = new QuestionBankController();
        System.out.println(questionBankController.createPaper("花花", "1000001921032"));
        System.out.println(questionBankController.createPaper("豆豆", "1000001921051"));
        System.out.println(questionBankController.createPaper("大宝", "1000001921987"));
    }

}
