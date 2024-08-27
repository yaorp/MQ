package com.mq.cloud;


import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: yaorp
 */
public class FilterProducer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("producer_group",true);
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();


        List<Order> orders1 = builderOrderList(1, "Buy", 4, "tag1");
        List<Order> orders2 = builderOrderList(5, "Pay", 4, "tag2");
        List<Order> orders3 = builderOrderList(9, "Finish", 4, "tag3");

        List<Order> objects = new ArrayList<>(9);
        objects.addAll(orders1);
        objects.addAll(orders2);
        objects.addAll(orders3);


        for (int i = 0; i < objects.size(); i++) {
            Order order = objects.get(i);
            Message msg = new Message(
                    "filter-Topic",
                    order.tag,
                    (order.toString()).getBytes());
            msg.setKeys("FILTER_KEY");
//            producer.send(msg, new MessageQueueSelector() {
//                @Override
//                public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
//                    int orderId = (int) o;
//                    int idx = orderId % list.size();
//                    return list.get(idx);
//                }
//            }, order.orderId);
            producer.send(msg);
        }

        System.out.println("发送成功");
    }

    public static List<Order> builderOrderList(int orderId, String des, int num, String tag) {
        List<Order> restList = new ArrayList<>(num);

        for (int i = 0; i < num; i++) {
            Order order = new Order();
            order.orderId = orderId++;
            order.desc = des;
            order.tag = tag;
            restList.add(order);
        }
        return restList;
    }

    public static class Order{
        int orderId;
        String desc;
        String tag;

        @Override
        public String toString() {
            return "orderId="+orderId+",desc="+desc+","+"tag="+tag;
        }
    }

}
