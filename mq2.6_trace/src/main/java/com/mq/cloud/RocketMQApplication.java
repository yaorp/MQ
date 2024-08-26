package com.mq.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;

/**
 * @author yaorp
 */
@SpringBootApplication
@EnableBinding({ Source.class, Sink.class })
public class RocketMQApplication {
	public static void main(String[] args) {
		SpringApplication.run(RocketMQApplication.class, args);
		System.out.println("【【【【【  RocketMQApplication 启动成功  】】】】】");
	}

	@StreamListener("input")
	public void receiveInput1(String receiveMsg) {
		System.out.println("input receive: " + receiveMsg);
	}

}