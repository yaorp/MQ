package com.mq.cloud;


import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: yaorp
 */
public class PartlyProducer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("producer_group",true);
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();


        List<Order> orders1 = builderOrderList(1, "Buy", "Pay", "Finish");
        List<Order> orders2 = builderOrderList(2, "Buy", "Pay");
        List<Order> orders3 = builderOrderList(3, "Pay", "Finish");

        List<Order> objects = new ArrayList<>(9);
        objects.addAll(orders1);
        objects.addAll(orders2);
        objects.addAll(orders3);


        for (int i = 0; i < objects.size(); i++) {
            Order order = objects.get(i);
            Message msg = new Message(
                    "Partly-Orderly-Topic",
                    "Partly_Orderly_Tag",
                    (order.toString()).getBytes());
            msg.setKeys("Partly_Orderly_Tag_KEY");
            producer.send(msg, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                    int orderId = (int) o;
                    int idx = orderId % list.size();
                    return list.get(idx);
                }
            }, order.orderId);
        }

        System.out.println("发送成功");
    }

    public static List<Order> builderOrderList(int orderId, String... descList) {
        List<Order> restList = new ArrayList<>(3);

        for (String s : descList) {
            Order order = new Order();
            order.orderId = orderId;
            order.desc = s;
            restList.add(order);
        }
        return restList;
    }

    public static class Order{
        int orderId;
        String desc;

        @Override
        public String toString() {
            return "orderId="+orderId+",desc="+desc;
        }
    }

}
