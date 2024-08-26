package com.mq.cloud;


import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

/**
 * @author: yaorp
 */
public class Producer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("producer_group",true);
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();

        for (int i = 0; i < 12; i++) {
            Message msg = new Message(
                    "test-spmc-topic",
                    "spmcTag",
                    ("第["+i+"]"+"message from mq2.7_spmc_producer producer").getBytes());
            msg.setKeys("SPMC");
            producer.send(msg);
        }

        System.out.println("发送成功");

    }
}
