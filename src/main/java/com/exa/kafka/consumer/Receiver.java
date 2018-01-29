package com.exa.kafka.consumer;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import com.exa.constants.Constants;
import com.exa.eventdao.ResponseDto;
import com.exa.kafka.producer.Sender;
import com.exa.pojo.EventContainerClass;
import com.exa.service.EventService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class Receiver {

	@Autowired
	private EventService eventService;

	@Autowired
	private Sender sender;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(Receiver.class);

	private CountDownLatch latch = new CountDownLatch(1);

	public CountDownLatch getLatch() {
		return latch;
	}

	@KafkaListener(topics = "${receivertopic.consumer}")
	public void receive(EventContainerClass eventContainerClass)
			throws JsonProcessingException {
		// EventContainerClass eventContainerClass = new EventContainerClass();
		LOGGER.info("Inside reciever method of Reciever");
		LOGGER.info("received event='{}'", eventContainerClass.toString());
		String json = null;
		EventContainerClass containerClass = null;
		List<EventContainerClass> containerList = null;
		ObjectWriter writer = new ObjectMapper().writer()
				.withDefaultPrettyPrinter();

		try {
			LOGGER.info("Calling process method of EventService");
			if (eventContainerClass.getOperation().equals(Constants.READALL))
				containerList = eventService.readAll(eventContainerClass);
			else
				containerClass = eventService.process(eventContainerClass);
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.error("Error while inserting data in cassandra : "
					+ e.getMessage());
		}
		if (containerList != null && containerClass != null) {
			if (eventContainerClass.getOperation().equals(Constants.READALL)) {
				json = writer.writeValueAsString(containerList);
				sender.send(new ResponseDto<EventContainerClass>(json, true));
			} else {
				json = writer.writeValueAsString(containerClass);
				sender.send(new ResponseDto<EventContainerClass>(json, true));
			}
		} else
			sender.send(new ResponseDto<EventContainerClass>(
					"Record Not Found", false));
		latch.countDown();
	}
}
