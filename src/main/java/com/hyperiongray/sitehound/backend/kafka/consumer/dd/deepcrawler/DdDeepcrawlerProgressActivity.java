package com.hyperiongray.sitehound.backend.kafka.consumer.dd.deepcrawler;

import com.hyperiongray.framework.kafka.service.Activity;
import com.hyperiongray.sitehound.backend.service.dd.deepcrawler.DdDeepcrawlerProgressBrokerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * Created by tomas on 28/09/16.
 */

@Component
public class DdDeepcrawlerProgressActivity implements Activity {
    private static final Logger LOGGER = LoggerFactory.getLogger(DdDeepcrawlerProgressActivity.class);

    @Autowired
    private DdDeepcrawlerProgressBrokerService ddDeepcrawlerProgressBrokerService;

    @KafkaListener(topics= "dd-deepcrawler-output-progress")
    public void listen(@Payload String data,
//                       @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) Integer key,
                       @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                       @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {

        LOGGER.info("received data:" + data.length() + ", partition:" + partition + ", topic:" + topic);
        LOGGER.debug("received data:" + data + ", partition:" + partition + ", topic:" + topic);
        ddDeepcrawlerProgressBrokerService.process(data);
    }

}
