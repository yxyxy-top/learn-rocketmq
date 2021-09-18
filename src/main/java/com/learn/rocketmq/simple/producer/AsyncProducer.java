package com.learn.rocketmq.simple.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.CountDownLatch2;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.concurrent.TimeUnit;

/**
 * 异步发送消息
 *
 * @author XuYu
 * @date 2020/11/3 10:30
 */
public class AsyncProducer {
    public static void main(String[] args) throws Exception {
        // 实例化消息生产者
        DefaultMQProducer producer = new DefaultMQProducer("test_group");
        // 设置Nameserver地址
        producer.setNamesrvAddr("localhost:9876");
        // 启动生产者
        producer.start();
        // 设置当发送异步失败重试时间
        producer.setRetryTimesWhenSendAsyncFailed(0);

        // 设置发送消息的数量
        int messageCount = 10;

        // 根据消息数量实例倒计时计算器
        final CountDownLatch2 countDownLatch = new CountDownLatch2(messageCount);
        for (int i = 0; i < messageCount; i++) {
            final int index = i;
            // 创建消息,并指定Topic,Tag和消息体
            Message message = new Message(
                    "Topic_A",
                    "TagA",
                    "OrderID188",
                    "Hello world".getBytes(RemotingHelper.DEFAULT_CHARSET));
            // SendCallback接受异步返回结果的回调
            producer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.printf("%-10d OK %s %n", index,sendResult.getMsgId());
                }

                @Override
                public void onException(Throwable e) {
                    System.out.printf("%-10d Exception %s %n", index, e);
                    e.printStackTrace();
                }
            });
        }
        // 等待5s
        countDownLatch.await(5, TimeUnit.SECONDS);
        // 如果不再发送消息,关闭Producer实例
        producer.shutdown();

    }
}