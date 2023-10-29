package ru.workwear.workwearshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping()
    public String homePage(Model model){
        model.addAttribute("index",1);
        return "home";
    }

    @GetMapping("/login")
    public String loginPage(Model model){
        model.addAttribute("index",7);
        return "home";
    }
}
