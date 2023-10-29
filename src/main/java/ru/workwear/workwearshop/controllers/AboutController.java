package ru.workwear.workwearshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/about")
public class AboutController {
    @GetMapping()
    public String aboutPage(Model model){
        model.addAttribute("index",5);
        return "home";
    }
}
