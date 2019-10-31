package com.app.ecom.store.events;

import java.util.Locale;

import com.app.ecom.store.dto.userservice.UserDto;
import org.springframework.context.ApplicationEvent;

public class ChangePasswordEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	private String url;

    private Locale locale;

    private UserDto userDto;

	public ChangePasswordEvent(String url, Locale locale, UserDto userDto) {
		super(userDto);
		this.url = url;
		this.locale = locale;
        this.userDto = userDto;
	}
	
	public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}
