package com.exa.pojo;

import java.io.Serializable;

public class Container implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String operation;
	private String type;
	private Tenant tenant;
	private User user;
	private Cohost cohost;
	private EventEntity eventEntity;
	private Assistant assistant;
	private Participants participant;

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Cohost getCohost() {
		return cohost;
	}

	public void setCohost(Cohost cohost) {
		this.cohost = cohost;
	}

	public EventEntity getEventEntity() {
		return eventEntity;
	}

	public void setEventEntity(EventEntity eventEntity) {
		this.eventEntity = eventEntity;
	}

	public Assistant getAssistant() {
		return assistant;
	}

	public void setAssistant(Assistant assistant) {
		this.assistant = assistant;
	}

	public Participants getParticipant() {
		return participant;
	}

	public void setParticipant(Participants participant) {
		this.participant = participant;
	}

}
