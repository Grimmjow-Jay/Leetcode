package practice.demo.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.nio.charset.StandardCharsets;

/**
 * @author Jay Yang
 * @date 2022/1/25
 */
public class NettyNioServer {

    public static void main(String[] args) throws Exception {
        new NettyNioServer()
                .server(4998);
    }

    public void server(int port) throws Exception {
        ByteBuf buf = Unpooled.unreleasableBuffer(
                Unpooled.copiedBuffer("Hi!\r\n", StandardCharsets.UTF_8));
        NioEventLoopGroup group = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap()
                    .group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(port)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ch.pipeline()
                                    .addLast(
                                            new ChannelInboundHandlerAdapter() {
                                                @Override
                                                public void channelActive(ChannelHandlerContext ctx) {
                                                    ctx.writeAndFlush(buf.duplicate())
                                                            .addListener(ChannelFutureListener.CLOSE);
                                                }
                                            }
                                    );
                        }
                    });
            ChannelFuture channelFuture = serverBootstrap.bind().sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }

    }

}
