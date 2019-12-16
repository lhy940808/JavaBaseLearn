package thread;

import java.util.concurrent.TimeUnit;

/**
 * @author liuhaiyan
 * @date 2019-11-22 10:40
 */
public class ThreadInterupt {
    public static void main(String[] args) throws InterruptedException{
        Thread thread = new Thread() {
            @Override
            public void run() {
                for(;;) {
                    System.out.println(Thread.interrupted());
                }
            }
        };
        thread.setDaemon(true);
        thread.start();

        TimeUnit.MILLISECONDS.sleep(2);
        thread.interrupt();
        System.out.println("main thread end");
    }
}
