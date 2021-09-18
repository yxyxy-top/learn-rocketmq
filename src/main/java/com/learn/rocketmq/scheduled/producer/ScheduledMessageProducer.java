package com.learn.rocketmq.scheduled.producer;

import com.learn.rocketmq.constant.RocketMQConstant;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

/**
 * 发送延时消息
 *
 * @author XuYu
 * @date 2020/11/4 15:18
 */
public class ScheduledMessageProducer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer(RocketMQConstant.PRODUCERGROUP);
        producer.setNamesrvAddr(RocketMQConstant.NAMESRVADDR);
        producer.start();

        int totalMessagesToSend = 10;
        for (int i = 0; i < totalMessagesToSend; i++) {
            Message msg = new Message(
                    RocketMQConstant.TOPIC,
                    RocketMQConstant.TAG,
                    ("Hello scheduled message " + i).getBytes()
            );
            msg.setDelayTimeLevel(3);
            producer.send(msg);
        }

        producer.shutdown();

    }

}