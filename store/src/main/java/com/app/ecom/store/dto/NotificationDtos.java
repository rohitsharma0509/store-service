package com.app.ecom.store.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(value = Include.NON_NULL)
public class NotificationDtos {
	
	@JsonProperty("notifications")
	private List<NotificationDto> notifications;

	public List<NotificationDto> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<NotificationDto> notifications) {
		this.notifications = notifications;
	}

}
