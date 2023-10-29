package ru.workwear.workwearshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contact")
public class ContactController {
    @GetMapping()
    public String contactPage(Model model){
        model.addAttribute("index",4);
        return "home";
    }
}
