package ru.workwear.workwearshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reviews")
public class ReviewsController {
    @GetMapping()
    public String reviewsPage(Model model){
        model.addAttribute("index",3);
        return "home";
    }
}
