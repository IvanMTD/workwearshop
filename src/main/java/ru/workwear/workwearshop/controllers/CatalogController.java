package ru.workwear.workwearshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/catalog")
public class CatalogController {
    @GetMapping()
    public String catalogPage(Model model){
        model.addAttribute("index",2);
        return "home";
    }
}
