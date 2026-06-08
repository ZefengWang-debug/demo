package com.example.demo.netty.handler;
import com.example.demo.netty.protocol.NettyChatMessage;
import com.example.demo.service.ChatMessageService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.stereotype.Component;

@Component
public class NettyChatServerHandler extends SimpleChannelInboundHandler<NettyChatMessage> {
    private final ChatMessageService chatMessageService;
    public NettyChatServerHandler(ChatMessageService chatMessageService) { this.chatMessageService = chatMessageService; }

    @Override protected void channelRead0(ChannelHandlerContext ctx, NettyChatMessage msg) {
        if ("CHAT".equalsIgnoreCase(msg.getType())) {
            chatMessageService.saveMessage(msg.getSessionId(), msg.getSenderId(), msg.getSenderType(), msg.getContent());
            NettyChatMessage ack = new NettyChatMessage();
            ack.setType("ACK");
            ack.setSessionId(msg.getSessionId());
            ack.setSenderId(msg.getSenderId());
            ack.setSenderType("SERVER");
            ack.setContent("message saved");
            ctx.writeAndFlush(ack);
        }
    }

    @Override public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
