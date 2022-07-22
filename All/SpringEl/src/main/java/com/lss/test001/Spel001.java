package com.lss.test001;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.List;
import java.util.Map;

public class Spel001 {
    public static void main(String[] args) {

        ExpressionParser parser = new SpelExpressionParser();

        //允许自动拼接
        String value = parser.parseExpression("'hello'+'world'").getValue(String.class);
        System.out.println(value);
        //直接使用string的方法使用
        String value1 = (String)parser.parseExpression("'hello'+'world'.split('')").getValue();
        System.out.println(value1);
        //允许自动拼接
        String value2 = parser.parseExpression("'hello'+'world'.concat('java')").getValue(String.class);
        System.out.println(value2);
        //纯数字是不需要加单引号的，可以用 + -等关系操作符直接操作
        Boolean boolean1 = parser.parseExpression("2==2").getValue(Boolean.class);
        System.out.println(boolean1);
        Integer  integer1 = parser.parseExpression("2+2").getValue(Integer .class);
        System.out.println(integer1);
        //list集合
        List value3 = (List) parser.parseExpression("{1,2,3,4}").getValue();
        System.out.println(value3);
        //list嵌套list
        List value4 = (List) parser.parseExpression("{{1,2,3,4},{5,6,7}}").getValue();
        System.out.println(value4);
        //map
        Map value5 = (Map) parser.parseExpression("{'userName':'lss','age':'18'}").getValue();
        System.out.println(value5);

        User user = new User();
        EvaluationContext context = new StandardEvaluationContext(user);
        //在context上下文中去找到方法并且调用
        //public method
        Boolean value6 = (Boolean)parser.parseExpression("method1('admin')").getValue(context);
        System.out.println(value6);
        //static method
        Boolean value7 = (Boolean)parser.parseExpression("method2('admin')").getValue(context);
        System.out.println(value7);
        //私有方法报错，找不到。奇了怪了，它不是用反射去操作找到数据和方法的吗？反射可以找到私有的哇，除非没有配置什么或者它没有这方面的操作
        /*Boolean value8 = (Boolean)parser.parseExpression("method3('admin')").getValue(context);
        //Method call: Method method3(java.lang.String) cannot be found on com.lss.User type
        System.out.println(value8);*/
        //给实体对象属性塞值
        parser.parseExpression("setUserName('lss')").getValue(context);
        System.out.println(user.getUserName());
        //直接给属性塞值
        parser.parseExpression("userName='lss01'").getValue(context);
        System.out.println(user.getUserName());
        //将属性name设置为空
        user.setUserName(null);
        //用三目运算符编写表达式
        String value9 =  parser.parseExpression("userName?:'other'").getValue(context,String.class);
        System.out.println(value9);

        //
        context.setVariable("tag","001");
        user.setUserName("lss");
        context.setVariable("user",user);
        String value41 = (String) parser.parseExpression("#user.userName").getValue(context,String.class);
        System.out.println(value41);
        String value51 = (String) parser.parseExpression("#tag").getValue(context,String.class);
        System.out.println(value51);
    }
}
