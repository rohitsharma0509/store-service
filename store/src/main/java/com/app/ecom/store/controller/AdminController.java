package com.app.ecom.store.controller;

import com.app.ecom.store.constants.RequestUrls;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping(value = RequestUrls.ADMIN)
    public String registration(Model model) {
        return "admin";
    }
}
