package ru.workwear.workwearshop.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.workwear.workwearshop.enums.Role;
import ru.workwear.workwearshop.models.Subject;

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

    @GetMapping("/logout")
    public String performLogout(Model model, Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, response, authentication);
        return "redirect:/";
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
