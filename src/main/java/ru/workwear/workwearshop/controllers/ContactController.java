package ru.workwear.workwearshop.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.workwear.workwearshop.models.Subject;

@Controller
@RequestMapping("/contact")
public class ContactController {
    @GetMapping()
    public String contactPage(Model model){
        model.addAttribute("index",4);
        return "home";
    }

    @ModelAttribute(name = "auth")
    public boolean auth(@AuthenticationPrincipal Subject user){
        if(user != null){
            if(user.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
}
