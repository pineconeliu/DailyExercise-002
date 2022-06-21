package application.lss;

import java.util.concurrent.*;

public class FutureTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {

        UserInfoService userInfoService = new UserInfoService();
        MedalService medalService = new MedalService();
        long userId =666L;
        long startTime = System.currentTimeMillis();
        ExecutorService poolExecutor = new ThreadPoolExecutor(2, 4, 2L, TimeUnit.SECONDS, new LinkedBlockingQueue());
        poolExecutor.execute(
                ()->{

                }
        );
        //调用用户服务获取用户基本信息
        CompletableFuture<UserInfo> completableUserInfoFuture = CompletableFuture.supplyAsync(() -> userInfoService.getUserInfo(userId));

        Thread.sleep(300); //模拟主线程其它操作耗时

        CompletableFuture<MedalInfo> completableMedalInfoFuture = CompletableFuture.supplyAsync(() -> medalService.getMedalInfo(userId),poolExecutor);

        UserInfo userInfo = completableUserInfoFuture.get(2, TimeUnit.SECONDS);//获取个人信息结果
        MedalInfo medalInfo = completableMedalInfoFuture.get();//获取勋章信息结果
        System.out.println("总共用时" + (System.currentTimeMillis() - startTime) + "ms");

    }
}