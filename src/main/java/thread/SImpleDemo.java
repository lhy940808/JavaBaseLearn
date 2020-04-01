package thread;

/**
 * @author liuhaiyan
 * @date 2019-12-06 17:10
 */
public class SImpleDemo {
    public static void main(String[] args) {
        System.out.println("main------");
        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "is running[----");
            for(int i = 0; i < 1000; i++){
                System.out.println(i + "hahah");
            }
        }).start();
        System.out.println("main end----");
    }


}

