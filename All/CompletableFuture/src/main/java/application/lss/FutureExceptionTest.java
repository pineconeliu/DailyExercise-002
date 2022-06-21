package application.lss;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * CompletableFuture的exceptionally方法表示，某个任务执行异常时，执行的回调方法;
 * 并且有抛出异常作为参数，传递到回调方法。会有返回值
 */
public class FutureExceptionTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<String> orgFuture = CompletableFuture.supplyAsync(
                ()->{
                    System.out.println("当前线程名称：" + Thread.currentThread().getName());
                    throw new RuntimeException();
                }
        );
//       Thread.currentThread().join();
//       可以让主线程挂起，执行到异步里面的方法，但是抛出的错误不能获取没有调用get

        CompletableFuture<String> exceptionFuture = orgFuture.exceptionally((e) -> {
            e.printStackTrace();
            System.out.println("执行到这个获取异常的线程中");
            return "你的程序异常啦";
        });

        int i=0;
//        System.out.println("asdsadsadasd");
//        System.out.println(exceptionFuture.get());
    }
}
