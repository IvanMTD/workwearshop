package ru.workwear.workwearshop.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.workwear.workwearshop.dto.SubjectDTO;
import ru.workwear.workwearshop.models.Role;
import ru.workwear.workwearshop.models.Subject;
import ru.workwear.workwearshop.services.SubjectService;
import ru.workwear.workwearshop.validation.SubjectValidator;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final SubjectValidator subjectValidator;
    private final SubjectService subjectService;
    private final PasswordEncoder passwordEncoder;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    public String adminPage(Model model){
        model.addAttribute("index",6);
        return "home";
    }

    @GetMapping("/registration")
    public String registrationPage(Model model){
        model.addAttribute("index",8);
        model.addAttribute("subject", new SubjectDTO());
        model.addAttribute("roles", Role.values());
        return "home";
    }

    @PostMapping("/registration")
    public String registrationConfirm(Model model, @ModelAttribute(name = "subject") @Valid SubjectDTO subjectDTO, Errors errors){
        subjectValidator.validate(subjectDTO,errors);
        if(errors.hasErrors()){
            model.addAttribute("index",8);
            model.addAttribute("roles", Role.values());
            return "home";
        }
        Subject subject = subjectService.save(subjectDTO,passwordEncoder);
        System.out.println(subject.toString());
        return "redirect:/";
    }

    @ModelAttribute(name = "auth")
    public boolean auth(@AuthenticationPrincipal User user){
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
