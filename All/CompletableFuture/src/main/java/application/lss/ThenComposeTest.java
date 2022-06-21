package application.lss;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * thenCompose
 *
 * thenCompose方法会在某个任务执行完成后，将该任务的执行结果,作为方法入参,去执行指定的方法。该方法会返回一个新的CompletableFuture实例
 *
 * 如果该CompletableFuture实例的result不为null，则返回一个基于该result新的CompletableFuture实例；
 * 如果该CompletableFuture实例为null，然后就执行这个新任务
 */
public class ThenComposeTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<String> f = CompletableFuture.completedFuture("第一个任务");
        //第二个异步任务
        ExecutorService executor = Executors.newSingleThreadExecutor();
        CompletableFuture<String> future = CompletableFuture
                .supplyAsync(() -> "第二个任务", executor)
                .thenComposeAsync(data -> {
                    System.out.println(data);
                    return f; //使用第一个任务作为返回
                }, executor);
        System.out.println(future.join());
        executor.shutdown();

    }
}