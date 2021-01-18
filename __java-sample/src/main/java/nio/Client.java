package nio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * <p>Created by Intellij IDEA.
 *
 * @author Eric Cui
 * @since 2019-03-15 22:40
 */
public class Client {

    public static void main(String[] args) {
        send(8080);
    }

    private static void send(int port) {
        try (SocketChannel socketChannel = SocketChannel.open();) {
            socketChannel.connect(new InetSocketAddress(port));

            String input;
            byte[] bytes = new byte[1024];
            while (true) {
                System.out.print("输入发送到server的信息：");
                if (System.in.read(bytes) > 0) {
                    input = new String(bytes).trim();

                    // 发送数据到server
                    socketChannel.write(ByteBuffer.wrap(bytes));

                    ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
                    // 获取server的相应信息,写入buffer
                    socketChannel.read(byteBuffer);
                    byteBuffer.flip();
                    System.out.println("ReceiveMsg From Server:\n" + new String(byteBuffer.array()).trim());
                    System.out.println();

                    if ("cancel_close".contains(input)
                            || "q".equalsIgnoreCase(input)) {
                        socketChannel.close();
                        break;
                    }
                    bytes = new byte[1024];
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }

    }
}
