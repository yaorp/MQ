package com.mq.cloud;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;

/**
 * @author: yaorp
 */
public interface CustomSource extends Source {

    /**
     * Name of the output channel.
     */
    String OUTPUT2 = "output2";

    /**
     * @return output channel
     */
    @Output(CustomSource.OUTPUT2)
    MessageChannel output2();
}
