package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>Created by Intellij IDEA.
 *
 * @author Eric Cui
 * @since 2019-03-15 22:18
 */
public class NIOServer {

    private static ExecutorService executor = Executors.newFixedThreadPool(4);

    public static void main(String[] args) {
        nioServer(8080);
    }

    private static void nioServer(int port) {
        try (Selector selector = Selector.open();
             ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
            serverSocketChannel.socket().bind(new InetSocketAddress(port));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, serverSocketChannel.validOps());
            System.out.println("server started.....");
            int clients = 0;
            String clientFmt = "client@[%d]";
            String client;
            while (true) {
                int readyOps = selector.select();
                // System.out.println(String.format("[%d] channels are ready for I/O operations。。。", readyOps));
                // System.out.println("有通道就绪了------11111");
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();

                    if (selectionKey.isAcceptable()) {
                        ServerSocketChannel acceptableChannel = (ServerSocketChannel) selectionKey.channel();
                        SocketChannel socketChannel = acceptableChannel.accept();
                        socketChannel.configureBlocking(false);

                        client = String.format(clientFmt, ++clients);
                        System.out.println(client + " is connected....");
                        // System.out.println("链接来了------22222");
                        socketChannel.register(selector, SelectionKey.OP_READ, client);
                    }
                    if (selectionKey.isReadable()) {
                        // System.out.println("要读内容了------33333");
                        executor.submit(new SocketChannelHandler(selectionKey));
                        // SocketChannel socketChannel = executor.submit(new SocketChannelCallable(selectionKey)).get();
                        // socketChannel.register(selector, SelectionKey.OP_READ);
                        selectionKey.cancel();
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }

    static class SocketChannelHandler implements Runnable {
        SelectionKey selectionKey;
        SocketChannelHandler(SelectionKey selectionKey) {
            this.selectionKey = selectionKey;
        }

        @Override
        public void run() {
            try {
                SocketChannel socketChannel = (SocketChannel) this.selectionKey.channel();
                socketChannel.configureBlocking(false);
                // System.out.println("开始读吧------44444");
                Object attachmentObj = this.selectionKey.attachment();
                String client;
                Attachment attachment;
                if (attachmentObj instanceof Attachment) {
                    attachment = (Attachment) attachmentObj;
                    client = attachment.getClient();
                } else {
                    client = (String) attachmentObj;
                    attachment = new Attachment(null, client, 0);
                }

                String input;
                while (true) {
                    // 默认写模式
                    ByteBuffer inputBuffer = ByteBuffer.allocate(1024);
                    // 从channel读出数据，写入buffer里面
                    if (socketChannel.read(inputBuffer) > 0) {
                        // System.out.println("读到数据了------55555");
                        attachment.increaseTimes();
                        // 切换buffer为读模式
                        inputBuffer.flip();
                        // 读出buffer中的数据
                        input = new String(inputBuffer.array()).trim();

                        String log = String.format("%s ### 第[%d]次从接收到数据:[%s]", client, attachment.getTimes(), input);
                        System.out.println(log);

                        String response = ("NIOServer:【" + input + "】");
                        // 从buffer中读出数据，写入channel
                        ByteBuffer resBuffer = ByteBuffer.wrap(response.getBytes(Charset.defaultCharset()));
                        socketChannel.write(resBuffer);

                        // 接收到结束指令
                        if ("cancel_close".contains(input)) {
                            if ("close".equalsIgnoreCase(input)) {
                                // 关闭socket
                                socketChannel.close();
                                System.out.println("socketChannel.close......");
                            }
                            if ("cancel".equalsIgnoreCase(input)) {
                                // 取消注册该事件
                                selectionKey.cancel();
                                System.out.println("selectionKey.cancel.....");
                            }
                            System.out.println("socketChannel.isOpen:" + socketChannel.isOpen());
                            break;
                        }
                    }
                    // 如果没有读到数据就讲该channel注册到selector上面 SelectionKey.OP_READ，并将该客户端作为attachment传过去
                    else {
                        /*System.out.println("没有读到数据------66666");

                        System.out.println("从selectionKey中获取selector ~~~~~~~~~~~~~~~");
                        Selector selector = selectionKey.selector();
                        System.out.println("selector.wakeup() ~~~~~~~~~~~~~~~");
                        selector.wakeup();

                        System.out.println("socketChannel.register ~~~~~~~~~~~~~~~");
                        socketChannel.register(selector, SelectionKey.OP_READ, attachment);

                        break;*/
                        System.out.println("没有收到数据。。。。");
                        Socket socket = socketChannel.socket();
                        socket.setSoTimeout(1000);
                        byte[] bytes = new byte[1024];
                        System.out.println("准备等1s试试看数据来了没。。。。");

                        try {
                            if (socket.getInputStream().read(bytes) > 0) {
                                System.out.println(new String(bytes));
                            }
                            System.out.println("不知道这里是什么情况");
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.println("不知道这里是什么情况");
                        }

                        System.out.println("看看这里会输出什么东西");
                    }
                    System.out.println("while loop 结束了@@@@@@@@@@@@@@@@@@@");
                }
                System.out.println("这一次结束了-----------##########");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
            }
        }
    }

    static class Attachment {
        private ByteBuffer byteBuffer;
        private String client;
        private int times;

        public Attachment(ByteBuffer byteBuffer, String client, int times) {
            this.byteBuffer = byteBuffer;
            this.client = client;
            this.times = times;
        }

        public ByteBuffer getByteBuffer() {
            return byteBuffer;
        }

        public void setByteBuffer(ByteBuffer byteBuffer) {
            this.byteBuffer = byteBuffer;
        }

        public String getClient() {
            return client;
        }

        public void setClient(String client) {
            this.client = client;
        }

        public int getTimes() {
            return times;
        }

        public void setTimes(int times) {
            this.times = times;
        }

        public void increaseTimes() {
            this.times += 1;
        }
    }

}
