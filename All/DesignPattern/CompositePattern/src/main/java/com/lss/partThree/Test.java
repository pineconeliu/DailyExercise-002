package com.lss.partThree;

//组合模式 共同的特性抽出来，为物品接口类
//每个子类实现对应的方法，通过互相组合
//模拟生活中袋子装货品，又可以装袋子的例子，大袋子看成根节点，小袋子看成子叶节点
// 牛奶看成果实节点，小袋子里面又有果实节点
public class Test {
    public static void main(String[] args) {
        Bag smallBag,bigBag;

        Goods goods = new Goods("方便面", 4.0);
        smallBag = new Bag();
        smallBag.putStuff(goods);
         goods = new Goods("鸡蛋", 2.0);
        smallBag.putStuff(goods);

        bigBag = new Bag();
        goods = new Goods("牛奶", 3.0);
        bigBag.putStuff(goods);

        bigBag.putStuff(smallBag);
        bigBag.show();
        Double aDouble = bigBag.totalPrice();

        System.out.println("总价格："+aDouble);
    }
}
