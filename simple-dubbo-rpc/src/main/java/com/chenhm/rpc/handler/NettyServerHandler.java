package com.chenhm.rpc.handler;

import com.chenhm.base.UrlKeys;
import com.chenhm.rpc.BaseCacheContext;
import com.chenhm.base.result.Result;
import com.chenhm.rpc.Exporter;
import com.chenhm.rpc.invocation.Invocation;
import com.chenhm.rpc.invoker.Invoker;
import com.chenhm.rpc.rpc.Request;
import com.chenhm.rpc.rpc.Response;
import com.chenhm.rpc.rpc.ResponseStatus;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author chen-hongmin
 * @since 2018/1/10 15:25
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 这里我们覆盖了chanelRead()事件处理方法。
     * 每当从客户端收到新的数据时，
     * 这个方法会在收到消息时被调用，
     *ChannelHandlerContext对象提供了许多操作，
     * 使你能够触发各种各样的I/O事件和操作。
     * 这里我们调用了write(Object)方法来逐字地把接受到的消息写入
     * @param ctx 通道处理的上下文信息
     * @param msg 接收的消息
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {


        if (msg instanceof Request){
            Request request = (Request)msg;
            Invocation invocation = request.getInvocation();
            String path = invocation.getAttachment(UrlKeys.PATH);
            Exporter exporter = BaseCacheContext.getExporter(path);
            Invoker invoker = exporter.getInvoker();
            Result result = invoker.invoke(invocation);

            Response response = new Response();
            response.setValue(result);
            response.setStatus(ResponseStatus.OK);

            ctx.writeAndFlush(response);
        }
    }


    /**
     * ctx.write(Object)方法不会使消息写入到通道上，
     * 他被缓冲在了内部，你需要调用ctx.flush()方法来把缓冲区中数据强行输出。
     * 或者你可以在channelRead方法中用更简洁的cxt.writeAndFlush(msg)以达到同样的目的
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
    /**
     * 这个方法会在发生异常时触发
     *
     * @param ctx
     * @param cause
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        /***
         * 发生异常后，关闭连接
         */
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println("有东西连接上来了");
        super.channelActive(ctx);
    }
}
