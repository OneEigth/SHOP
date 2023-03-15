package oneeight.shop.controller;

import oneeight.shop.entity.AppUser;
import oneeight.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {


    @Autowired
    private UserService userService;

    @GetMapping(path = "/registration")
    public String registrationPage(Model model) {
        model.addAttribute("appUser", new AppUser());
        return "registration_page";
    }

    @PostMapping(path = "/registration")
    public String registrationAction(@ModelAttribute AppUser appUser) {
        userService.registerUser(appUser);
        return "filter_page";
    }
}
