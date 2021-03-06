package com.hyperiongray.sitehound.backend.integration.kafka.consumer.mocks;

import com.hyperiongray.sitehound.backend.integration.kafka.consumer.ImportUrlFlowTest;
import com.hyperiongray.sitehound.backend.kafka.api.dto.Metadata;
import com.hyperiongray.sitehound.backend.kafka.producer.LocalQueueProducer;
import com.hyperiongray.sitehound.backend.model.Queues;
import com.hyperiongray.framework.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by tomas on 2/4/16.
 */
@Component
public class ImportUrlProducerMock {


	@Autowired
	private LocalQueueProducer localQueueProducer;

	private JsonMapper jsonMapper = new JsonMapper();

	private static final Logger LOGGER = LoggerFactory.getLogger(ImportUrlProducerMock.class);

//	@Override
//	protected String getInputQueue(){
//		return importUrlInputQueue;
//	}

//	@Override
	public void submit(Metadata metadata, String url) throws IOException{

		ImportUrlFlowTest.ImportUrl importUrl = new ImportUrlFlowTest.ImportUrl(metadata, url, true);
		String importUrlString = jsonMapper.toJson(importUrl);
		localQueueProducer.send(Queues.IMPORT_URL_INPUT.getSubscriberTopic(), importUrlString);
		LOGGER.info("Sent to aquarium"  + importUrlString +" with url:" + importUrl.getUrl());
	}
}
