package io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

import static common.ConstantEnum.FALSE;
import static common.ConstantEnum.PORT;

/**
 * Created by cuiguiyang on 2017/3/12 21:27.
 * Desc
 */
public class NIO {

    public static void main(String[] args) {

//        selector();

        fileChannel();

    }

    public static void fileChannel() {
        try {
            RandomAccessFile aFile = new RandomAccessFile("note.txt", "rw");
            FileChannel inChannel = aFile.getChannel();

            /*ByteBuffer byteBuffer = ByteBuffer.allocate(64);
            byteBuffer.put("落霞与孤鹜齐飞，秋水共长天一色".getBytes(UTF8));
            inChannel.write(byteBuffer);*/

            ByteBuffer buf = ByteBuffer.allocate(48);

            int bytesRead = inChannel.read(buf);
            while (bytesRead != -1) {
                System.out.println("Read " + bytesRead);
                buf.flip();

                while (buf.hasRemaining()) {
                    System.out.print("-----" + (char) buf.get());
                }

                buf.clear();
                bytesRead = inChannel.read(buf);
            }
            aFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void selector() {
        try {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            Selector selector = Selector.open();
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.configureBlocking(FALSE);//设置为非阻塞方式
            ssc.socket().bind(new InetSocketAddress(PORT));
            ssc.register(selector, SelectionKey.OP_ACCEPT);//注册监听的事件
            while (true) {
                Set selectedKeys = selector.selectedKeys();//取得所有key集合
                Iterator it = selectedKeys.iterator();
                while (it.hasNext()) {
                    SelectionKey key = (SelectionKey) it.next();
                    if ((key.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {
                        ServerSocketChannel ssChannel = (ServerSocketChannel) key.channel();
                        SocketChannel sc = ssChannel.accept();//接受到服务端的请求
                        sc.configureBlocking(FALSE);
                        sc.register(selector, SelectionKey.OP_READ);
                        it.remove();
                    } else if ((key.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ) {
                        SocketChannel sc = (SocketChannel) key.channel();
                        while (true) {
                            buffer.clear();
                            int n = sc.read(buffer);//读取数据
                            if (n <= 0) {
                                break;
                            }
                            buffer.flip();
                        }
                        it.remove();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
