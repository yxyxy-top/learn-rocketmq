package com.learn.rocketmq.simple.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.nio.charset.StandardCharsets;

/**
 * 消息消费
 *
 * @author XuYu
 * @date 2020/11/2 16:06
 */
public class Consumer {
    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("test_group");
        consumer.setNamesrvAddr("localhost:9876");
        consumer.subscribe("Topic_A","*");
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
//            System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
            if (msgs.stream().findFirst().isPresent()){
                MessageExt messageExt = msgs.stream().findFirst().get();
                String body = new String(messageExt.getBody(), StandardCharsets.UTF_8);
                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), body);
            }
            // 标记该消息已经被成功消费
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        // 启动消费者实例
        consumer.start();
        System.out.printf("Consumer Started.%n");
    }
}