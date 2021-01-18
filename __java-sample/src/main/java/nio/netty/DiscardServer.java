package nio.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;

/**
 * Created by Intellij IDEA.
 *
 * @author Eric Cui
 * @since 2019-03-31 15:21
 */
@SuppressWarnings("all")
public class DiscardServer {

    public void run(int port) throws Exception {
        NioEventLoopGroup boos = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            final ChannelInboundHandlerAdapter channelInboundHandlerAdapter =
                    new ChannelInboundHandlerAdapter() {
                        @Override
                        public void channelRead(ChannelHandlerContext ctx, Object msg)
                                throws Exception {
                            ByteBuf byteBuf = (ByteBuf) msg;
                            System.out.println(byteBuf.toString(CharsetUtil.UTF_8));

                            System.out.println("ChannelInboundHandlerAdapter.channelRead()");
                            ctx.write(
                                    Unpooled.copiedBuffer(
                                            "ChannelInboundHandlerAdapter.channelRead()".getBytes(CharsetUtil.UTF_8)));
                        }

                        @Override
                        public void channelReadComplete(ChannelHandlerContext ctx)
                                throws Exception {
                            System.out.println("...channelReadComplete");
                            ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
                        }
                    };
            bootstrap
                    .group(boos, worker)
                    .channel(NioServerSocketChannel.class) // (3)
                    .childHandler(
                            new ChannelInitializer<SocketChannel>() { // (4)
                                @Override
                                public void initChannel(SocketChannel ch) throws Exception {
                                    ch.pipeline().addLast(new DiscardServerHandler(), channelInboundHandlerAdapter);
                                }
                            })
                    .option(ChannelOption.SO_BACKLOG, 128) // (5)
                    .childOption(ChannelOption.SO_KEEPALIVE, true); // (6)

            // Bind and start to accept incoming connections.
            ChannelFuture f = bootstrap.bind(port).sync(); // (7)
            System.out.println("ChannelFuture.....");

            // Wait until the server socket is closed.
            // In this example, this does not happen, but you can do that to gracefully
            // shut down your server.
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            worker.shutdownGracefully();
            boos.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        new DiscardServer().run(8080);
    }

    @ChannelHandler.Sharable
    class DiscardServerHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf in = (ByteBuf) msg;
            System.out.println(in.toString(CharsetUtil.UTF_8));
            System.out.println("DiscardServerHandler.channelRead()");
            ctx.fireChannelRead(msg);
            ctx.fireChannelRead("DiscardServerHandler.channelRead()");
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            super.exceptionCaught(ctx, cause);
            ctx.close();
        }
    }
}
