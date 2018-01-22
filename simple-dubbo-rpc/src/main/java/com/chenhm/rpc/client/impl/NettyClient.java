package com.chenhm.rpc.client.impl;

import com.chenhm.base.URL;
import com.chenhm.base.constant.BizConstants;
import com.chenhm.rpc.client.AbstractClient;
import com.chenhm.rpc.handler.NettyClientHandler;
import com.chenhm.rpc.serialize.MarshallingCodecFactory;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chen-hongmin
 * @since 2018/1/10 15:35
 */
public class NettyClient extends AbstractClient {

    private static final Logger log = LoggerFactory.getLogger(NettyClient.class);
    private EventLoopGroup workerGroup;

    public NettyClient(URL url) {

        super(url);
    }

    @Override
    public void doOpen() throws InterruptedException {

        workerGroup = new NioEventLoopGroup();

        /**
         * 如果你只指定了一个EventLoopGroup，
         * 那他就会即作为一个‘boss’线程，
         * 也会作为一个‘workder’线程，
         * 尽管客户端不需要使用到‘boss’线程。
         */
        Bootstrap b = new Bootstrap();
        b.group(workerGroup);
        /**
         * 代替NioServerSocketChannel的是NioSocketChannel,这个类在客户端channel被创建时使用
         */
        b.channel(NioSocketChannel.class);
        /**
         * 不像在使用ServerBootstrap时需要用childOption()方法，
         * 因为客户端的SocketChannel没有父channel的概念。
         */
        b.option(ChannelOption.SO_KEEPALIVE, false);

        NettyClientHandler nettyHandler = new NettyClientHandler(this);

        b.handler(new ChannelInitializer() {
            @Override
            protected void initChannel(Channel ch) throws Exception {
                ch.pipeline().addLast(MarshallingCodecFactory.buildMarshallingDecoder());
                ch.pipeline().addLast(MarshallingCodecFactory.buildMarshallingEncoder());
                ch.pipeline().addLast(nettyHandler);
            }
        });
        //用connect()方法代替了bind()方法
        b.connect(host, port).sync();
        setConnect(true);
        log.info("connect {} successful", BizConstants.CONSUMER + "://" + url.getHost() + "?address=" + host + ":" + port + "&interface=" + interfaceName);
    }

    @Override
    public void close() {
        if (!isConnect()) {
            throw new RuntimeException("this workerGroup not connect!");
        }

        workerGroup.shutdownGracefully();
        setConnect(false);
    }


}
