package application.lss;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class FutureStreamTest {

    public static void main(String[] args) {
        List<String> arrayList = new ArrayList<>();
        for(int i=0;i<100;i++){
            arrayList.add(i+"");
       }
        long startTime = System.currentTimeMillis();
        System.out.println(startTime);
        arrayList.forEach(e->{
            try {
                Thread.sleep(10);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            System.out.println(e);
        });

        System.out.println("总共用时" + (System.currentTimeMillis() - startTime) + "ms");

        long startTime1 = System.currentTimeMillis();
        System.out.println(startTime1);
        List<CompletableFuture<Object>> collect = arrayList.stream().map(e -> CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(10);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            System.out.println(e);
            return null;
        })).collect(Collectors.toList());

     collect.forEach(e->e.join());

        System.out.println("总共用时" + (System.currentTimeMillis() - startTime1) + "ms");

        
    }
}
