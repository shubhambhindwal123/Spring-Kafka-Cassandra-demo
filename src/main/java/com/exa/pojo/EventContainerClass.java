package com.exa.pojo;

import java.util.List;
import java.util.UUID;

public class EventContainerClass 
{
	public EventContainerClass() {
		super();
	}

	private boolean privateevent;

    private String location;

    private String tag;

    private String repeat;

    private String type;

   private List<String> participants;

    private UUID eventid;

    private String currency;

    private Cohost cohost;

    private String operation;

    private String title;

    private boolean paid;

    private Assistant assistant;

    private double price;

    private List<String> days;

  //  private Map<String,String> tenant;

    private Tenant tenant;
    private String description;

    private String repeatend;
    
    private byte[] cover;

    private User user;
    
    private  UUID organisation;

	public boolean isPrivateevent() {
		return privateevent;
	}

	public void setPrivateevent(boolean privateevent) {
		this.privateevent = privateevent;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getRepeat() {
		return repeat;
	}

	public void setRepeat(String repeat) {
		this.repeat = repeat;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getParticipants() {
		return participants;
	}

	public void setParticipants(List<String> participants) {
		this.participants = participants;
	}

	public UUID getEventid() {
		return eventid;
	}

	public void setEventid(UUID eventid) {
		this.eventid = eventid;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Cohost getCohost() {
		return cohost;
	}

	public void setCohost(Cohost cohost) {
		this.cohost = cohost;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public Assistant getAssistant() {
		return assistant;
	}

	public void setAssistant(Assistant assistant) {
		this.assistant = assistant;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public List<String> getDays() {
		return days;
	}

	public void setDays(List<String> days) {
		this.days = days;
	}

	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRepeatend() {
		return repeatend;
	}

	public void setRepeatend(String repeatend) {
		this.repeatend = repeatend;
	}

	public byte[] getCover() {
		return cover;
	}

	public void setCover(byte[] cover) {
		this.cover = cover;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UUID getOrganisation() {
		return organisation;
	}

	public void setOrganisation(UUID organisation) {
		this.organisation = organisation;
	}

	public EventContainerClass(boolean privateevent, String location, String tag, String repeat, String type,
			List<String> participants, UUID eventid, String currency, Cohost cohost, String operation, String title,
			boolean paid, Assistant assistant, double price, List<String> days, Tenant tenant,
			String description, String repeatend, byte[] cover, User user, UUID organisation) {
		super();
		this.privateevent = privateevent;
		this.location = location;
		this.tag = tag;
		this.repeat = repeat;
		this.type = type;
		this.participants = participants;
		this.eventid = eventid;
		this.currency = currency;
		this.cohost = cohost;
		this.operation = operation;
		this.title = title;
		this.paid = paid;
		this.assistant = assistant;
		this.price = price;
		this.days = days;
		this.tenant = tenant;
		this.description = description;
		this.repeatend = repeatend;
		this.cover = cover;
		this.user = user;
		this.organisation = organisation;
	}

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

   }
