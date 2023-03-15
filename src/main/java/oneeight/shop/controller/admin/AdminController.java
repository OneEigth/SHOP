package oneeight.shop.controller.admin;

import lombok.RequiredArgsConstructor;
import oneeight.shop.service.OrderService;
import oneeight.shop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor

public class AdminController {
    private final OrderService orderService;
    private final UserService userService;

    @GetMapping(path = "/admin")
    public String adminPanel() {

        return "admin_page";
    }

    @GetMapping(path = "/login")
    public String login() {
        return "login_page";
    }

    @GetMapping(path = "/admintest")
    public String testAdminPanel() {
        return "test_admin_page";
    }

}
