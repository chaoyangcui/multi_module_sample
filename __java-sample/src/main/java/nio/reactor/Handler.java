package nio.reactor;


import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * 状态模式实现NIO的读、写
 * <p>Created by Intellij IDEA.
 *
 * @author Eric Cui
 * @since 2019-03-30 11:37
 */
public class Handler implements Runnable {
    final SelectionKey selectionKey;
    final SocketChannel socketChannel;

    ByteBuffer input = ByteBuffer.allocate(1024);
    ByteBuffer output = ByteBuffer.allocate(1024);

    public Handler(Selector selector, SocketChannel socketChannel) throws IOException {
        this.socketChannel = socketChannel;
        socketChannel.configureBlocking(false);
        selectionKey = socketChannel.register(selector, 0);
        selectionKey.interestOps(SelectionKey.OP_READ);
        selectionKey.attach(this);
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
        System.out.println("receive:" + new String(input.array()));
    }

    @Override
    public void run() {
        try {
            input.clear();
            socketChannel.read(input);
            if (inputIsComplete()) {
                process();
                selectionKey.interestOps(SelectionKey.OP_WRITE);
                selectionKey.attach(new Sender());
                selectionKey.selector().wakeup();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class Sender implements Runnable {
        @Override
        public void run() {
            try {
                output.clear();
                output.put("MSG From Server".getBytes(Charset.defaultCharset()));
                output.flip();
                socketChannel.write(output);
                if (outputIsComplete()) {
                    // 取消注册 @see「方法注释」
                    selectionKey.cancel();
                }
                // 切换为读模式
                else {
                    selectionKey.interestOps(SelectionKey.OP_READ);
                    selectionKey.attach(new Handler(selectionKey.selector(), socketChannel));
                    selectionKey.selector().wakeup();
                }
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    socketChannel.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}

