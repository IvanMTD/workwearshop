package ru.workwear.workwearshop.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.workwear.workwearshop.dto.CategoryDTO;
import ru.workwear.workwearshop.dto.ProductDTO;
import ru.workwear.workwearshop.dto.SubjectDTO;
import ru.workwear.workwearshop.enums.Role;
import ru.workwear.workwearshop.models.Product;
import ru.workwear.workwearshop.models.Subject;
import ru.workwear.workwearshop.services.CategoryService;
import ru.workwear.workwearshop.services.SubjectService;
import ru.workwear.workwearshop.validation.SubjectValidator;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final SubjectValidator subjectValidator;
    private final SubjectService subjectService;
    private final CategoryService categoryService;
    private final PasswordEncoder passwordEncoder;

    // ========================================== BASE ADMIN PAGE ====================================================

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping()
    public String adminPage(Model model){
        model.addAttribute("index",6);
        model.addAttribute("subjects",subjectService.findAll());
        return "home";
    }

    // =========================================== SUBJECT CONTROL ====================================================

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/subject/{id}")
    public String subjectPage(Model model, @PathVariable(name = "id") long id){
        model.addAttribute("index",9);
        model.addAttribute("subject",subjectService.findById(id));
        return "home";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/registration")
    public String registrationPage(Model model){
        model.addAttribute("index",8);
        model.addAttribute("subject", new SubjectDTO());
        model.addAttribute("roles", Role.values());
        return "home";
    }

    @PreAuthorize("hasRole('ADMIN')")
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

    // =============================================== CATEGORY ======================================================

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/category")
    public String categoryCreatorPage(Model model){
        model.addAttribute("index",10);
        model.addAttribute("category", new CategoryDTO());
        return "home";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/category")
    public String categoryAdd(Model model, @ModelAttribute(name = "category") @Valid CategoryDTO category, Errors errors){
        if(errors.hasErrors()){
            model.addAttribute("index",10);
            return "home";
        }
        categoryService.save(category);
        return "redirect:/admin";
    }

    //============================================= PRODUCT CONTROL ==================================================

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/product")
    public String productCreatorPage(Model model){
        model.addAttribute("index",12);
        model.addAttribute("product", new ProductDTO());
        model.addAttribute("categories", categoryService.findAllLazy());
        return "home";
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping("/product")
    public String productAdd(@ModelAttribute(name = "product") ProductDTO product,@RequestParam(name = "select") long id){
        System.out.println(product);
        return "redirect:/admin";
    }

    //============================================== MODEL ATTRIBUTE =================================================

    @ModelAttribute(name = "auth")
    public boolean auth(@AuthenticationPrincipal Subject subject) {
        boolean isAdmin = authenticate(subject,Role.ROLE_ADMIN.getRole());
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
