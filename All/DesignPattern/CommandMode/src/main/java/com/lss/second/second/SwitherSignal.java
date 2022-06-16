package com.lss.second.second;

//生成一个电信号的类去实现功能
public class SwitherSignal implements command{

    private AbstractLifeGoods abstractLifeGoods;

    public SwitherSignal(AbstractLifeGoods abstractLifeGood) {
        this.abstractLifeGoods = abstractLifeGood;
    }

    @Override
    public void exec() {
        System.out.println("执行一个打开的命令");
        abstractLifeGoods.onLight();
    }

    @Override
    public void reExec(){
        System.out.println("执行一个关闭的的命令号");
        abstractLifeGoods.downLight();
    }
}
