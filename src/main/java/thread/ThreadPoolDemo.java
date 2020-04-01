package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author liuhaiyan
 * @date 2020-01-07 11:41
 */
public class ThreadPoolDemo {

    public int add (int number) {
        return number + 3;
    }

    public static ExecutorService executorService = Executors.newFixedThreadPool(4);

    public static void main(String[] args) throws Exception{
        List<Future<Integer>> future = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            MyCallable call = new MyCallable(i);
            Future<Integer> result = executorService.submit(call);
            future.add(result);
        }
        for (Future<Integer> f : future) {
            Integer res = f.get();
            System.out.println("res is " + res);
        }

    }


}


class MyCallable implements Callable {
    public int number;

    public MyCallable(int number) {
        this.number = number;
    }

    @Override
    public Integer call() throws Exception {
        return new ThreadPoolDemo().add(number);

    }
}