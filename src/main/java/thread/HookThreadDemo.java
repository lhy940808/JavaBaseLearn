package thread;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * ,防止某个程序被重复启动，在进程启动时会创建一个lock文件，进程收到中断信号的时候会删除这个文件git
 * @author liuhaiyan
 * @date 2019-11-25 20:40
 */
public class HookThreadDemo {
    private final static String LOCK_PARH = "/Users/liuhaiyan05/lhy/github_code/JavaBaseLearn/target/";
    private final static String LOCK_FILE = "a.lock";
    private final static String PERMISSIONS = "rw-------";

    public static void main(String[] args) throws IOException{
        checkRunning();
        // 注入hook线程
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("the program received kill signal");
            getLockFile().toFile().delete();

        }));

        // 模拟程序运行
        for(;;) {
            try {
                TimeUnit.MILLISECONDS.sleep(1);
                System.out.println("program is running");
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void checkRunning() throws IOException {
        Path path = getLockFile();
        if (path.toFile().exists()) {
            throw new RuntimeException("the program already running...");
        }
        Set<PosixFilePermission> perms = PosixFilePermissions.fromString(PERMISSIONS);
        Files.createFile(path, PosixFilePermissions.asFileAttribute(perms));
    }

    public static Path getLockFile() {
        return Paths.get(LOCK_PARH, LOCK_FILE);
    }


}
