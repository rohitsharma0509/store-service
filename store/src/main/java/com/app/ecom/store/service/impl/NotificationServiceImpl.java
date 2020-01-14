package com.app.ecom.store.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.app.ecom.store.dto.NotificationDto;
import com.app.ecom.store.dto.NotificationDtos;
import com.app.ecom.store.service.NotificationService;

public class NotificationServiceImpl implements NotificationService {

	@Override
	public NotificationDtos getNotifications() {
		NotificationDtos notificationDtos = new NotificationDtos();
		
		List<NotificationDto> notifications = new ArrayList<>();
		
		/*NotificationDto notificationDto = new NotificationDto();
		notificationDto.setCount(12L);
		notificationDto.setMessage("You have 12 tickets assiged. Please take action");
		notifications.add(notificationDto);*/
		
		notificationDtos.setNotifications(notifications);
		return notificationDtos;
	}

}
