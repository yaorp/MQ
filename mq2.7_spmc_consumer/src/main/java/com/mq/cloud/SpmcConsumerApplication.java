package com.mq.cloud;

import org.springframework.beans.factory.annotation.Value;
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
public class SpmcConsumerApplication {

	@Value("${server.port}")
	private String port;

	public static void main(String[] args) {
		SpringApplication.run(SpmcConsumerApplication.class, args);
		System.out.println("【【【【【  SpmcConsumerApplication 启动成功  】】】】】");
	}


	@StreamListener("input")
	public void receiveInput1(String receiveMsg){
		System.out.println("input receive:"+receiveMsg);
	}


}