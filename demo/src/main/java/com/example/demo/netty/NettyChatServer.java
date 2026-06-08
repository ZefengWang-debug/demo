package com.example.demo.netty;
import com.example.demo.netty.handler.NettyChatServerHandler;
import com.example.demo.netty.protocol.NettyMessageDecoder;
import com.example.demo.netty.protocol.NettyMessageEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;

@Component
public class NettyChatServer implements CommandLineRunner {
    @Value("${netty.server.port:8011}")
    private int port;
    private final NettyChatServerHandler handler;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;

    public NettyChatServer(NettyChatServerHandler handler) { this.handler = handler; }

    @Override public void run(String... args) {
        new Thread(this::start, "netty-chat-server").start();
    }

    public void start() {
        bossGroup = new NioEventLoopGroup(1);
        workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override protected void initChannel(SocketChannel ch) {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(new LengthFieldBasedFrameDecoder(1024 * 1024, 0, 4, 0, 4));
                            p.addLast(new StringDecoder(StandardCharsets.UTF_8));
                            p.addLast(new NettyMessageDecoder());
                            p.addLast(new LengthFieldPrepender(4));
                            p.addLast(new StringEncoder(StandardCharsets.UTF_8));
                            p.addLast(new NettyMessageEncoder());
                            p.addLast(handler);
                        }
                    });
            ChannelFuture f = bootstrap.bind(port).sync();
            System.out.println("Netty chat server started on port " + port);
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            shutdown();
        }
    }

    @PreDestroy public void shutdown() {
        if (bossGroup != null) bossGroup.shutdownGracefully();
        if (workerGroup != null) workerGroup.shutdownGracefully();
    }
}
