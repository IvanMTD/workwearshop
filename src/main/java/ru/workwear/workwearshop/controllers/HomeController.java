package ru.workwear.workwearshop.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
