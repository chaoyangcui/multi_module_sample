package nio.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * <p>Created by Intellij IDEA.
 *
 * @author Eric Cui
 * @since 2019-03-30 11:00
 */
@SuppressWarnings("all")
public class Reactor implements Runnable {
    final Selector selector;
    final ServerSocketChannel serverSocketChannel;

    public static void main(String[] args) throws IOException {
        new Reactor(8080).run();
    }

    public Reactor(int port) throws IOException {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(port));

        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        selectionKey.attach(new Acceptor());
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                selector.select();
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    System.out.println("accept .....");
                    dispatch(iterator.next());
                    iterator.remove();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dispatch(SelectionKey selectionKey) {
        Runnable r = (Runnable) selectionKey.attachment();
        if (r != null) {
            // FIXME 2019-03-31 这里调用的是run方法，而不是启动了一个线程
            r.run();
        }
    }

    /**
     * 连接处理器，负责请求分发
     */
    class Acceptor implements Runnable {
        @Override
        public void run() {
            try {
                SocketChannel socketChannel = serverSocketChannel.accept();
                if (socketChannel != null) {
                    // new Handler(selector, socketChannel);
                    new nio.reactor.Handler(selector, socketChannel);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 业务处理器，负责处理请求读写
     */
    class Handler implements Runnable {
        final SelectionKey selectionKey;
        final SocketChannel socketChannel;
        ByteBuffer input = ByteBuffer.allocate(1024);
        ByteBuffer output = ByteBuffer.allocate(1024);

        static final int READING = 0, SENDING = 1;
        int state = READING;

        public Handler(Selector selector, SocketChannel socketChannel) throws IOException {
            this.socketChannel = socketChannel;
            socketChannel.configureBlocking(false);
            // Optionally try first read now
            selectionKey = socketChannel.register(selector, 0);
            selectionKey.attach(this);
            selectionKey.interestOps(SelectionKey.OP_READ);
            selector.wakeup();
        }

        boolean inputIsComplete() {
            /* ... */
            return true;
        }
        boolean outputIsComplete() {
            /* ... */
            return true;
        }
        void process() {
            input.flip();
            System.out.println(new String(input.array()));
        }

        @Override
        public void run() {
            try {
                if (state == READING) { read(); }
                if (state == SENDING) { send(); }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void read() throws IOException {
            output.clear();
            socketChannel.read(input);
            if (inputIsComplete()) {
                process();
                state = SENDING;
                // Normally also do first write now
                selectionKey.interestOps(SelectionKey.OP_WRITE);
            }
        }

        public void send() throws IOException {
            output.clear();
            output.put("MSG From Server".getBytes());
            output.flip();
            socketChannel.write(output);
            if (outputIsComplete()) {
                // 不结束继续转为读模式
                // selectionKey.cancel();
                state = READING;
                selectionKey.interestOps(SelectionKey.OP_READ);
            }
        }
    }
}

