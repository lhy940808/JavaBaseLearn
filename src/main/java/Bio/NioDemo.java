package Bio;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

/**
 * @author liuhaiyan
 * @date 2019-11-20 19:30
 */
public class NioDemo {


    // 使用nio的方式进行文件拷贝
    public static void copyFIle(String srcFilename, String dstFilename) throws IOException {
        FileInputStream fis = new FileInputStream(srcFilename);
        FileInputStream fos = new FileInputStream(dstFilename);

        FileChannel readChannel = fis.getChannel();
        FileChannel writeChannel = fos.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        for(;;) {
            byteBuffer.clear(); //清空缓存区
            // 从输入channel中读取数据到buffer中
            if (readChannel.read(byteBuffer) == -1) {
                break;
            }
            byteBuffer.flip();
            writeChannel.write(byteBuffer);// 将缓存区的数据写入到输出channel中

        }
        fis.close();
        fos.close();
    }

    /**
     * @Description : NIO模型下的TCP服务器的实
     * @params
     * @return
     * @author liuhaiyan
     * @date 2019-11-20 19:47
     */
    private static int TIMEOUT = 3000;
    public static void nioTcpServer(int listenPort) throws IOException {
        // 创建一个选择器
        Selector selector = Selector.open();
        //实例化一个信道
        ServerSocketChannel listenChannel = ServerSocketChannel.open();
        // 将该信道绑定到实行端口
        listenChannel.socket().bind(new InetSocketAddress(listenPort));
        // 配置信道为非阻塞模式
        listenChannel.configureBlocking(false);
        //将选择器注册到这个信道
        listenChannel.register(selector, SelectionKey.OP_ACCEPT);

        // 不断轮询select方法，获取准备好的的信道所关联的key集
        for(;;) {
            // 一直等待，直至有信道准备好了i/o操作
            if (selector.select(TIMEOUT) == 0) {
                // 在等待信道准备的同时也可以异步执行其他任务
                continue;
            }
            // 获取准备好的信道所关联的key集合的iterator实例
            Iterator<SelectionKey> keyIter = selector.selectedKeys().iterator();

            // 循环取得集合中的每个键值
            while(keyIter.hasNext()) {
                SelectionKey key = keyIter.next();
                // 如果服务端信道感兴趣的i/0操作为accept
                if (key.isAcceptable()) {
                    handleAccept(key);
                }

                // 如果客户端信道感兴趣的io操作是read
                if (key.isReadable()) {
                    handleAccept(key);
                }
                 if(key.isValid() && key.isWritable()) {
                     handleAccept(key);
                 }

                 // 这里需要手动从键集里移除当前key
                keyIter.remove();
            }

        }
    }

    public static void handleAccept(SelectionKey key) {

    }

}


