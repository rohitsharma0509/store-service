package com.app.ecom.store.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.app.ecom.store.client.SupportTicketClient;
import com.app.ecom.store.constants.FieldNames;
import com.app.ecom.store.constants.RequestUrls;
import com.app.ecom.store.dto.NotificationDto;
import com.app.ecom.store.dto.NotificationDtos;
import com.app.ecom.store.dto.userservice.UserDto;
import com.app.ecom.store.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("notificationService")
public class NotificationServiceImpl implements NotificationService {
	
	@Autowired
	private SupportTicketClient supportTicketClient;
	
	@Autowired
	private HttpSession httpSession;

	@Override
	public NotificationDtos getNotifications() {
		UserDto userDto = (UserDto) httpSession.getAttribute(FieldNames.USER);
		
		NotificationDtos notificationDtos = new NotificationDtos();
		
		List<NotificationDto> notifications = new ArrayList<>();
		
		Long noOfTickets = supportTicketClient.countUnclosedSupportTickets(userDto.getUsername());
		if(null != noOfTickets && noOfTickets > 0) {
			NotificationDto notificationDto = new NotificationDto();
			notificationDto.setCount(noOfTickets);
			notificationDto.setMessage(new StringBuilder("You have <a href='").append(RequestUrls.SUPPORT_TICKETS).append("?unclosedFor=").append(userDto.getUsername()).append("'>").append(noOfTickets).append("</a> tickets assigned. Kindly take necessary action.").toString());
			notifications.add(notificationDto);
		}
		
		notificationDtos.setNotifications(notifications);
		return notificationDtos;
	}

}
