package com.example.demo.netty.protocol;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import java.util.List;

public class NettyMessageEncoder extends MessageToMessageEncoder<NettyChatMessage> {
    private final ObjectMapper mapper = new ObjectMapper();
    @Override protected void encode(ChannelHandlerContext ctx, NettyChatMessage msg, List<Object> out) throws Exception {
        out.add(mapper.writeValueAsString(msg));
    }
}
