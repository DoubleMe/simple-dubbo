package com.chenhm.rpc.future;

import com.chenhm.base.exception.RpcException;
import com.chenhm.base.exception.TimeoutException;
import com.chenhm.rpc.channel.Channel;
import com.chenhm.rpc.rpc.Response;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chen-hongmin
 * @since 2018/1/16 15:19
 */
public class ResponseFuture implements Future {

    private static final long DEFAULT_TIME_OUT = 1000000;
    private static final Map<Channel, ResponseFuture> FUTURES = new ConcurrentHashMap<>();

    private final Lock lock = new ReentrantLock();

    private final Condition done = lock.newCondition();

    private volatile Response response;

    private long timeout;

    public ResponseFuture(Channel channel) {
        this(channel, DEFAULT_TIME_OUT);
    }

    public ResponseFuture(Channel channel, long timeout) {
        this.timeout = timeout;
        FUTURES.put(channel,this);
    }

    @Override
    public Object get() throws RpcException {
        return get(timeout);
    }

    @Override
    public Object get(long timeout) throws RpcException {

        if (timeout <= 0) {
            timeout = DEFAULT_TIME_OUT;
        }
        if (!isDone()) {
            long start = System.currentTimeMillis();
            lock.lock();
            try {
                while (!isDone()) {
                    done.await(timeout, TimeUnit.MILLISECONDS);
                    if (isDone() || System.currentTimeMillis() - start > timeout) {
                        break;
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
            if (!isDone()) {
                throw new TimeoutException();
            }
        }
        return returnFromResponse();
    }

    private Object returnFromResponse() {
        Response res = response;
        if (res == null) {
            throw new IllegalStateException("response cannot be null");
        }

        return res.getValue();
    }

    @Override
    public boolean isDone() {
        return response != null;
    }

    public static void received(Channel channel, Response response) {

        ResponseFuture future = FUTURES.remove(channel);
        if (future != null) {
            future.doReceived(response);
        }
    }

    private void doReceived(Response res) {
        lock.lock();
        try {
            response = res;
            if (done != null) {
                done.signal();
            }
        } finally {
            lock.unlock();
        }
    }
}
