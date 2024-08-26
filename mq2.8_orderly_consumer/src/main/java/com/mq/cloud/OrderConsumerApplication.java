package com.mq.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 * @author yaorp
 */
@SpringBootApplication
@EnableBinding({ CustomSink.class })
public class OrderConsumerApplication {
	public static void main(String[] args) {
		SpringApplication.run(OrderConsumerApplication.class, args);
		System.out.println("【【【【【  OrderConsumerApplication 启动成功  】】】】】");
	}

	@StreamListener("input")
	public void receiveInput1(String receiveMsg) {
		System.out.println("input receive: " + receiveMsg);
	}

	@StreamListener("input2")
	public void receiveInput2(String receiveMsg) {
		System.out.println("input receive2: " + receiveMsg);
	}
}