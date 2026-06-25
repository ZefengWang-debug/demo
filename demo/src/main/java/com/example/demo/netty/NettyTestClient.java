package com.example.demo.netty;

import com.example.demo.netty.protocol.NettyChatMessage;
import com.example.demo.netty.protocol.NettyMessageDecoder;
import com.example.demo.netty.protocol.NettyMessageEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import java.nio.charset.StandardCharsets;

public class NettyTestClient {
    public static void main(String[] args) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
                @Override protected void initChannel(SocketChannel ch) {
                    ChannelPipeline p = ch.pipeline();
                    p.addLast(new LengthFieldBasedFrameDecoder(1024 * 1024, 0, 4, 0, 4));
                    p.addLast(new StringDecoder(StandardCharsets.UTF_8));
                    p.addLast(new NettyMessageDecoder());
                    p.addLast(new LengthFieldPrepender(4));
                    p.addLast(new StringEncoder(StandardCharsets.UTF_8));
                    p.addLast(new NettyMessageEncoder());
                    p.addLast(new SimpleChannelInboundHandler<NettyChatMessage>() {
                        @Override protected void channelRead0(ChannelHandlerContext ctx, NettyChatMessage msg) {
                            System.out.println("server response: " + msg.getType() + " - " + msg.getContent());
                        }
                    });
                }
            });
            Channel channel = bootstrap.connect("127.0.0.1", 8011).sync().channel();
            NettyChatMessage msg = new NettyChatMessage();
            msg.setType("CHAT"); msg.setSessionId(1L); msg.setSenderId(1L); msg.setSenderType("USER"); msg.setContent("hello netty and shiro week");
            channel.writeAndFlush(msg).sync();
            Thread.sleep(1000);
            channel.close();
        } finally { group.shutdownGracefully(); }
    }
}
