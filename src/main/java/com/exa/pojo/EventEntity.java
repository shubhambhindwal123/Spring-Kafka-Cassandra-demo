package com.exa.pojo;

public class EventEntity {

	private String eventId;

	private String title;

	private String description;

	private String location;

	private String tag;

	private String cover;

	private boolean paid;

	private float price;

	private String currency;

	private boolean privateEvent;

	private String repeat;

	private int[] days = new int[10];

	private String repeatEnd;

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public boolean isPrivateEvent() {
		return privateEvent;
	}

	public void setPrivateEvent(boolean privateEvent) {
		this.privateEvent = privateEvent;
	}

	public String getRepeat() {
		return repeat;
	}

	public void setRepeat(String repeat) {
		this.repeat = repeat;
	}

	public int[] getDays() {
		return days;
	}

	public void setDays(int[] days) {
		this.days = days;
	}

	public String getRepeatEnd() {
		return repeatEnd;
	}

	public void setRepeatEnd(String repeatEnd) {
		this.repeatEnd = repeatEnd;
	}

}
