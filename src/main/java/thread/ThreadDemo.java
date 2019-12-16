package thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用线程模拟叫号，最多只能有50个号码
 * @author liuhaiyan
 * @date 2019-11-21 17:28
 */
public class ThreadDemo {
    private static AtomicInteger number = new AtomicInteger(0);

    public static void main(String[] args) {

        // 开启四个线程
        for (int i = 0; i < 4; i++) {
            new Thread() {
                @Override
                public void run() {
                    for(;;) {
                        if (number.get() <= 500) {
                            System.out.println("thread = " + Thread.currentThread().getName() + "get number is " + number.incrementAndGet() );
                        }else {
                            return;
                        }

                    }
                }

            }.start();
        }

    }

}
