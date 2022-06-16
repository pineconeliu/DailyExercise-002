import com.lss.HadelService;
import com.lss.State;

public class Test {
    public static void main(String[] args) {
        //本质上就是什么类调什么方法，写一个处理类，类中可以利用map的特性，用枚举类枚举想要操作的类，用map.get()方法获取想要的类对象，
        //然后在处理类方法中用get获取的类对象调用它对应的实现方法
        HadelService hadelService = new HadelService();
        hadelService.bianji(State.bianji);
        hadelService.tishen(State.bianji);
        hadelService.pass(State.bianji);
        hadelService.bianji(State.tishen);
        hadelService.cheshen(State.tishen);
        hadelService.pass(State.tishen);
    }

}
