package com.learn.rocketmq.transaction.consumer;

import com.learn.rocketmq.constant.RocketMQConstant;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;

/**
 * 事务消息消费者
 *
 * @author XuYu
 * @date 2020/11/4 17:00
 */
public class TransactionConsumer {

    public static void main(String[] args) throws MQClientException {

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(RocketMQConstant.PRODUCERGROUP);
        consumer.setNamesrvAddr(RocketMQConstant.NAMESRVADDR);
        consumer.subscribe(RocketMQConstant.TOPIC, "*");
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            msgs.forEach(message -> System.out.println("consumeThread=" + Thread.currentThread().getName() + "queueId=" + message.getQueueId() + ", content:" + new String(message.getBody())));
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        consumer.start();
    }
}