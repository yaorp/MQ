package com.mq.cloud;


import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

/**
 * @author: yaorp
 */
public class GlobalProducer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("producer_group",true);
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();

        for (int i = 0; i < 12; i++) {
            Message msg = new Message(
                    "Global-Orderly-Topic",
                    "Global_Orderly_Tag",
                    ("第["+i+"]"+"message from GlobalProducer").getBytes());
            msg.setKeys("Global_Orderly_Tag_KEY");
            producer.send(msg);
        }

        System.out.println("发送成功");

    }
}
