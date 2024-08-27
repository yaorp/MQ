package com.mq.cloud;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: yaorp
 */
public class Producer {
    public static void main(String[] args) throws Exception{
        // 1.创建生产者对象
        DefaultMQProducer producer = new DefaultMQProducer("delay_producer_group");
        // 2。生产者对象设置NameServer 地址
        producer.setNamesrvAddr("127.0.0.1:9876");

        // 把我们的生产者直接启动起来
        producer.start();

        // 创建消息，发送消息。
        for (int i = 0; i < 3; i++) {
//            String topic, String tags, String keys, byte[] body
            Message message = new Message(
                    "custom-delay-topic",
                    "delayTag",
                    "CUSTOM_DELAY",
                    ("("+i+") Hello Message From Delay Producer," +
                            "date="+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSS").format(new Date())).getBytes()
            );

            // 设置定时逻辑
            message.setDelayTimeLevel(2);

            //
            producer.send(message);
        }

    }
}
