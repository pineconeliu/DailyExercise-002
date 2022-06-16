package lsstest;

import com.Visitor;
import com.lss.*;

import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {
        Test test = new Test();
        ParentVisitor parentVisitor = new ParentVisitor();
        PrimaryVisitor primaryVisitor = new PrimaryVisitor();
        test.show(parentVisitor);
        test.show(primaryVisitor);

    }

    public void show(Visitor visitor) {
        ArrayList<User> list = new ArrayList<>();
        list.add(new Student("STN01", "1ban"));
        list.add(new Student("STN02", "2ban"));
        list.add(new Student("STN03", "3ban"));
        list.add(new Student("STN04", "4ban"));
        list.add(new Teacher("TN01", "1ban"));
        list.add(new Teacher("TN02", "2ban"));
        list.add(new Teacher("TN03", "3ban"));
        list.add(new Teacher("TN04", "4ban"));

        for (User u : list) {
            //所有可以共用的特征，都是可以通过抽象类或接口来代替，通常是定义完成这层关系，不直接表明具体是什么方法,具体的就又你输入的具体实现类来完成。
            u.accpect(visitor);
        }
    }

}
