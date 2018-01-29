package com.exa.kafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.exa.eventdao.ResponseDto;
import com.exa.pojo.EventContainerClass;

@Component
public class Sender {

	private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

	@Value("${topic.producer}")
	private String producerTopic;

	@Autowired
	private KafkaTemplate<String, EventContainerClass> kafkaTemplate;
	@Autowired
	private KafkaTemplate<String, ResponseDto<EventContainerClass>> kafkaResponse;

	public void send(EventContainerClass jsonData) {
		LOGGER.info("Inside send EventContainer method of Sender");
		LOGGER.info("sending data='{}'", jsonData.toString());
		kafkaTemplate.send("consumer", jsonData);
		LOGGER.info("Exiting send EventContainer method of Sender");
	}

	public void send(ResponseDto<EventContainerClass> jsonData) {
		LOGGER.info("Inside send response method of Sender");
		LOGGER.info("sending data='{}'", jsonData.toString());
		kafkaResponse.send(producerTopic, jsonData);
		LOGGER.info("Exiting send response method of Sender");
	}

}
