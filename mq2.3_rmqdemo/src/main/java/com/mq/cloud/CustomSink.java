package com.mq.cloud;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author: yaorp
 */
public interface CustomSink extends Sink {
    String INPUT2 = "input2";

    /**
     * @return input channel.
     */
    @Input(CustomSink.INPUT2)
    SubscribableChannel input2();
}
