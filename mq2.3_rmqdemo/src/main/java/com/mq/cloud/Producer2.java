package com.mq.cloud;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

/**
 * @author: yaorp
 */
public class Producer2 {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("producer_group2");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();

        for (int i = 0; i < 3; i++) {
            Message msg = new Message(
                    "test-topic2",
                    "tagStr",
                    ("第["+i+"]"+"message from rocketmq producer2").getBytes());
            producer.send(msg);
        }

    }
}
