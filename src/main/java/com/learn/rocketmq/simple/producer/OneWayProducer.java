package com.learn.rocketmq.simple.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * 单向发送消息
 *
 * @author XuYu
 * @date 2020/11/3 14:04
 */
public class OneWayProducer {
    public static void main(String[] args) throws Exception {
        // 实例化消息生产者
        DefaultMQProducer producer = new DefaultMQProducer("test_group");
        // 设置Nameserver的地址
        producer.setNamesrvAddr("localhost:9876");
        // 启动生产者
        producer.start();

        for (int i = 0; i < 10; i++) {
            // 创建消息，并指定Topic，Tag和消息体
            Message msg = new Message("Topic_A",
                    "TagA",
                    ("Hello RocketMQ " +
                            i).getBytes(RemotingHelper.DEFAULT_CHARSET)
            );
            // 发送单向消息，没有任何返回结果
            producer.sendOneway(msg);
        }

        // 如果不再发送消息，关闭Producer实例。
        producer.shutdown();

    }
}