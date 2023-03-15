package oneeight.shop.controller.admin;

import lombok.RequiredArgsConstructor;
import oneeight.shop.entity.AppUser;
import oneeight.shop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/admin/user")
public class AdminUserController {
    private final UserService userService;

    @GetMapping(path = "/deleteUser")
    public String delUser(@RequestParam AppUser appUser) {
        userService.deleteUser(appUser);
        return "redirect:/admin/role";
    }


}
