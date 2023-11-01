package ru.workwear.workwearshop.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.workwear.workwearshop.enums.Role;
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
    public boolean auth(@AuthenticationPrincipal Subject subject) {
        boolean isAdmin = authenticate(subject, Role.ROLE_ADMIN.getRole());
        boolean isUser = authenticate(subject,Role.ROLE_USER.getRole());
        return isAdmin || isUser;
    }

    @ModelAttribute(name = "admin")
    public boolean admin(@AuthenticationPrincipal Subject subject) {
        return authenticate(subject,Role.ROLE_ADMIN.getRole());
    }

    private boolean authenticate (Subject subject, String role){
        if (subject != null) {
            if (subject.getRole().getRole().equals(role)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
