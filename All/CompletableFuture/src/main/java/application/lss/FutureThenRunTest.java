package application.lss;

import java.util.concurrent.*;

/**
 * CompletableFuture的thenRun方法，通俗点讲就是，做完第一个任务后，再做第二个任务。
 * 某个任务执行完成后，执行回调方法；但是前后两个任务没有参数传递，第二个任务也没有返回值
 * 如果你执行第一个任务的时候，传入了一个自定义线程池：
 * 调用thenRun方法执行第二个任务时，则第二个任务和第一个任务是共用同一个线程池。
 * 调用thenRunAsync执行第二个任务时，则第一个任务使用的是你自己传入的线程池，第二个任务使用的是ForkJoin线程池
 * TIPS: 后面介绍的thenAccept和thenAcceptAsync，thenApply和thenApplyAsync等，它们之间的区别也是这个哈。
 */
public class FutureThenRunTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService poolExecutor = new ThreadPoolExecutor(2, 4, 2L, TimeUnit.SECONDS, new LinkedBlockingQueue());
        CompletableFuture<String> orgFuture = CompletableFuture.supplyAsync(
                ()->{
                    System.out.println("先执行第一个CompletableFuture方法任务");
                    System.out.println("当前线程id："+Thread.currentThread().getName());
                    return "捡田螺的小男孩";
                },poolExecutor
        );

        CompletableFuture thenRunFuture = orgFuture.thenRun(() -> {
            System.out.println("当前线程id："+Thread.currentThread().getName());
            System.out.println("接着执行第二个任务");
        });

        System.out.println(thenRunFuture.get());

        CompletableFuture<String> orgFuture1 = CompletableFuture.supplyAsync(
                ()->{
                    System.out.println("先执行第一个CompletableFuture方法任务");
                    System.out.println("当前线程id："+Thread.currentThread().getName());
                    return "捡田螺的小男孩";
                },poolExecutor
        );

        CompletableFuture thenRunFuture1 = orgFuture1.thenRunAsync(() -> {
            System.out.println("当前线程id："+Thread.currentThread().getName());
            System.out.println("接着执行第二个任务");
        });

        System.out.println(thenRunFuture1.get());


    }
}