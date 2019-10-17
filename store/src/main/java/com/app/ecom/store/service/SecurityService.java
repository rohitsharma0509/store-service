package com.app.ecom.store.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}
