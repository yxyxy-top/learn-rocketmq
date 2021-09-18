package com.learn.rocketmq.transaction.producer;

import com.learn.rocketmq.constant.RocketMQConstant;
import com.learn.rocketmq.transaction.listener.TransactionListenerImpl;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 事务消息生产者
 *
 * @author XuYu
 * @date 2020/11/4 16:23
 */
public class TransactionProducer {

    public static void main(String[] args) throws MQClientException, InterruptedException {
        TransactionListenerImpl transactionListener = new TransactionListenerImpl();
        TransactionMQProducer producer = new TransactionMQProducer(RocketMQConstant.PRODUCERGROUP);
        producer.setNamesrvAddr(RocketMQConstant.NAMESRVADDR);
        ExecutorService executorService = new ThreadPoolExecutor(
                2,
                5,
                5,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2000),
                r -> {
                    Thread thread = new Thread(r);
                    thread.setName("client-transaction-msg-check-thread");
                    return thread;
                });
        producer.setExecutorService(executorService);
        producer.setTransactionListener(transactionListener);
        producer.start();


        String[] tags = new String[]{"TagA", "TagB", "TagC", "TagD", "TagE"};
        for (int i = 0; i < 10; i++) {
            Message message = new Message(
                    RocketMQConstant.TOPIC,
                    tags[i % tags.length],
                    "Key" + i,
                    ("Hello RocketMQ " + i).getBytes(StandardCharsets.UTF_8)
                    );
            SendResult sendResult = producer.sendMessageInTransaction(message, i);
            System.out.printf("%s%n", sendResult);
        }
    }

}