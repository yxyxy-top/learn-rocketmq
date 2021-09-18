package com.learn.rocketmq.transaction.listener;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 事务监听实现
 *
 * @author XuYu
 * @date 2020/11/4 16:25
 */
public class TransactionListenerImpl implements TransactionListener {

    private final AtomicInteger transactionIndex = new AtomicInteger(0);
    private final ConcurrentHashMap<String, Integer> localTrans = new ConcurrentHashMap<>();
    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        int value = transactionIndex.getAndIncrement();
        // 设定状态以出现不同的效果.
        int status = value % 3;
        System.out.println("executeLocalTransaction + " + status);
        localTrans.put(msg.getTransactionId(), status);
        return LocalTransactionState.UNKNOW;
    }
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        Integer status = localTrans.get(msg.getTransactionId());
        if (null != status) {
            switch (status) {
                case 1:
                    System.out.println("LocalTransactionState.COMMIT_MESSAGE");
                    return LocalTransactionState.COMMIT_MESSAGE;
                case 2:
                    System.out.println("LocalTransactionState.ROLLBACK_MESSAGE");
                    return LocalTransactionState.ROLLBACK_MESSAGE;
                default:
                    System.out.println("LocalTransactionState.UNKNOW");
                    return LocalTransactionState.UNKNOW;
            }
        }
        return LocalTransactionState.COMMIT_MESSAGE;
    }
}