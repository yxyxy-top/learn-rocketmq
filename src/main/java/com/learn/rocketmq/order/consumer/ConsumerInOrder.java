package com.learn.rocketmq.order.consumer;

import com.learn.rocketmq.constant.RocketMQConstant;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;

/**
 * 顺序消息消费，带事务方式（应用可控制Offset什么时候提交）
 *
 * @author XuYu
 * @date 2020/11/3 14:32
 */
public class ConsumerInOrder {
    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(RocketMQConstant.PRODUCERGROUP);
        consumer.setNamesrvAddr(RocketMQConstant.NAMESRVADDR);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

        consumer.subscribe(RocketMQConstant.TOPIC,"TagA || TagC || TagB");

        consumer.registerMessageListener((MessageListenerOrderly) (msgs, context) -> {
            context.setAutoCommit(true);
            msgs.forEach(msg -> {
                // 可以看到每个queue有唯一的consume线程来消费, 订单对每个queue(分区)有序
                System.out.println("consumeThread=" + Thread.currentThread().getName() + "queueId=" + msg.getQueueId() + ", content:" + new String(msg.getBody()));
            });

            return ConsumeOrderlyStatus.SUCCESS;
        });

        consumer.start();

        System.out.println("Consumer Started.");
    }
}