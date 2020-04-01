package thread.future;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * java8 新特性--CompleteableFuture的实用
 * @author liuhaiyan
 * @date 2020-01-07 13:48
 */
public class CompleteableFutureDemo {

    private static Random random = new Random();
    private static long time = System.currentTimeMillis();

    public static int getMoreData(){
        System.out.println("begin to start compute");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("end to compute, passed:" + System.currentTimeMillis());
        return random.nextInt(1000);
    }

    public static int throwException(){
        System.out.println("准备抛出异常");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("抛了");
        throw new RuntimeException("主动抛出异常");
    }

    public static void exception1() throws Exception{

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> throwException());

        future.whenCompleteAsync((result, exception) -> {
            System.out.println("计算已执行完毕，result:" + result);
            System.out.println("计算已执行完毕，exception:" + (exception == null ? "无异常" : exception.getClass()));
        }).exceptionally(exception -> {
            System.out.println("计算执行过程中发生了异常，exception:" + exception.getClass());
            return 0;
        });

        System.out.println("执行到最后一段代码了，future result：" + future.get());
    }

    public static void main(String[] args) throws Exception{
        exception1();
    }

}
