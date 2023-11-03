package ru.workwear.workwearshop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.workwear.workwearshop.dto.ProductDTO;
import ru.workwear.workwearshop.enums.Role;
import ru.workwear.workwearshop.models.Subject;
import ru.workwear.workwearshop.services.CategoryService;
import ru.workwear.workwearshop.utils.ImageEncryptUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/catalog")
@RequiredArgsConstructor
public class CatalogController {

    private final CategoryService categoryService;

    @GetMapping()
    public String catalogsPage(Model model, @AuthenticationPrincipal Subject subject){
        model.addAttribute("index",2);
        model.addAttribute("categories", categoryService.findAllLazy(auth(subject)));
        return "home";
    }

    @GetMapping("/{id}")
    public String catalogProductsPage(Model model, @PathVariable(name = "id") long id){
        // .... =====================
        List<ProductDTO> products = new ArrayList<>();

        for(int i=0; i<10; i++){
            ProductDTO product = new ProductDTO();
            product.setId(i);
            product.setName("TEST" + i);
            product.setDescription("SUPERTEST" + i);
            product.setCoast(new BigDecimal("1000"));
            product.setImage(ImageEncryptUtil.loadImage("./src/main/resources/static/images/logo.png"));
            products.add(product);
        }
        // ..... ======================

        model.addAttribute("index",11);
        model.addAttribute("products", products);
        return "home";
    }

    //===============================================================================================================

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
