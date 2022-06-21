package com.lss.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CGLibExample {

    static class Car implements  Test{
        @Override
        public void running() {
            System.out.println("The car is running.");
        }
    }

    static interface  Test{
        public void running();
    }


    /**
     * CGLib 代理类
     */
    static class CGLibProxy  implements  MethodInterceptor {
        private Object target; // 要代理得对象

        public Object getInstance(Object target) {
            this.target = target;
            Enhancer enhancer = new Enhancer();
            // 设置父类为实例类
            enhancer.setSuperclass(this.target.getClass());
            // 回调方法
            enhancer.setCallback(this);
            // 创建代理对象
            return enhancer.create();
        }
        @Override
        public Object intercept(Object o, Method method,
                                Object[] objects, MethodProxy methodProxy) throws Throwable {
            // method 这个没有用到
            System.out.println(method);
            System.out.println("方法调用前业务处理.");

            Object result1 = method.invoke(target, objects);

//            Object result = methodProxy.invokeSuper(o, objects); // 执行方法调用

            return result1;
        }

    }

    // 执行 CGLib 的方法调用
    public static void main(String[] args) {
        // 创建 CGLib 代理类
        CGLibProxy proxy = new CGLibProxy();
        // 初始化代理对象
        Test car = (Car) proxy.getInstance(new Car());
        // 执行方法
         car.running();
    }

}