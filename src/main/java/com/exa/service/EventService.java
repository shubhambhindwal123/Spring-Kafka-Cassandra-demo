package com.exa.service;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.exa.constants.Constants;
import com.exa.eventdao.EventDao;
import com.exa.kafka.producer.Sender;
import com.exa.pojo.EventContainerClass;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
@Component
public class EventService {
	EventContainerClass jsonNode;
	
	@Autowired
	private Sender sender;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EventService.class);
	
	
	public EventContainerClass process(EventContainerClass eventOperation) throws JsonProcessingException, IOException{
	
		EventContainerClass containerClass = new EventContainerClass();
		
		EventDao daoCaller=new EventDao();
		boolean result = false ;
		
		if (eventOperation.getOperation().equals(Constants.ADD)) {
			LOGGER.info("Operation is ADD");
			containerClass = daoCaller.insert(eventOperation);
		}
		if (eventOperation.getOperation().equals(Constants.UPDATE)) {
			LOGGER.info("Operation is UPDATE");
			containerClass = daoCaller.update(eventOperation);
		}

		if (eventOperation.getOperation().equals(Constants.DELETE)) {
			LOGGER.info("Operation is DELETE");
			containerClass = daoCaller.delete(eventOperation);
		}

		if (eventOperation.getOperation().equals(Constants.READ)) {
			LOGGER.info("Operation is READ");
			containerClass = daoCaller.readByEvent(eventOperation);
		}

		return containerClass;
		
	}
	
	public List<EventContainerClass> readAll(EventContainerClass eventOperation) {
		
		EventDao daoCaller=new EventDao();
		LOGGER.info("Inside readAll method of Event Service");

		LOGGER.info("Operation is ReadAll");
		List<EventContainerClass> classes = daoCaller.readAll(eventOperation);
		return classes;

	}
	
}
