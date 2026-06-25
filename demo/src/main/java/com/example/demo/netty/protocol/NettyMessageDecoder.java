package com.example.demo.netty.protocol;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import java.util.List;

public class NettyMessageDecoder extends MessageToMessageDecoder<String> {
    private final ObjectMapper mapper = new ObjectMapper();
    @Override
    protected void decode(ChannelHandlerContext ctx, String msg, List<Object> out) throws Exception {
        out.add(mapper.readValue(msg, NettyChatMessage.class));
    }
}
