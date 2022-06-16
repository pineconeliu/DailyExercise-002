package com.lss;

/**
 * 定义一个双重校验加锁的单例模式 ，然后让反射去破坏
 */
public class ReflexSington {
    private static ReflexSington reflexSington = null;

    private ReflexSington() {
        // 因为反射创建实例的过程也是通过构造函数实现的，那我们可以再构造函数中做限制不让其创建实例
        if(reflexSington !=null){
           throw new RuntimeException("改实例已存在，不允许再创建");
        }
    }

    private static  ReflexSington getSington(){
        if(reflexSington ==null){
            synchronized (ReflexSington.class){
                if(reflexSington ==null){
                    reflexSington = new ReflexSington();
                }
            }
        }
        return reflexSington;
    }

    public static void main(String[] args) {
        //通过单例对外提供获取实例的方法获取实例
        ReflexSington sington = getSington();

        Class<ReflexSington> reflexSingtonClass = ReflexSington.class;
        try {
            // 通过反射获取的实例
            ReflexSington reflexSington = reflexSingtonClass.newInstance();
            System.out.println(sington);
            System.out.println(reflexSington);
            //结果是false，说明反射可以破坏单例模式来创建属于自己的实例
            System.out.println(reflexSington == sington);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
