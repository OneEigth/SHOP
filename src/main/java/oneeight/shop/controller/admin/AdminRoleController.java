package oneeight.shop.controller.admin;

import lombok.RequiredArgsConstructor;
import oneeight.shop.entity.AppUser;
import oneeight.shop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/admin/role")
public class AdminRoleController {
    private final UserService userService;

    @GetMapping
    public String setAdminRole(Model model) {
        model.addAttribute("users", userService.allUsers());
        return "admin_set_role_page";
    }

    @GetMapping(path = "/set")
    public String setAdminRoleAction(@RequestParam AppUser appUser) {
        userService.setAdminRole(appUser);
        return "redirect:/admin/role";
    }

}