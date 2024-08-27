package com.mq.cloud;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @author: yaorp
 */
public class ConsumerOrderAll {
    public static void main(String[] args) throws Exception{
        // 创建消费者对象
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumer_group");

        // 为消费者对象设置nameSrv地址
        consumer.setNamesrvAddr("127.0.0.1:9876");

        // 订阅主题
        consumer.subscribe("filter-Topic","*");

        // 注册监听消息，并打印消息
        consumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                for (MessageExt msg : msgs) {
//                    String printMsg = new String(msg.getBody()) +", revTime:"
//                            +new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
//                    System.out.println(printMsg);
                    System.out.println(new String(msg.getBody()));

                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });

        consumer.start();
        System.out.println("consumerOrder 启动成功");
    }
}
