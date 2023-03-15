package oneeight.shop.controller;

import lombok.RequiredArgsConstructor;
import oneeight.shop.service.FeedbackService;
import oneeight.shop.service.OrderProductsService;
import oneeight.shop.service.OrderService;
import oneeight.shop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class UserController {
    private final OrderService orderService;
    private final UserService userService;
    private final OrderProductsService orderProductsService;
    private final FeedbackService feedbackService;

    @GetMapping(path = "/user")
    public String userOrders(Model model) {
        model.addAttribute("orders", orderService.allOrdersByUser(userService.getCurrentUser()));
        model.addAttribute("feedbacks",feedbackService.userFeedbacks(userService.getCurrentUser()));
        return "user_page";
    }

    @GetMapping(path = "/user/orderProducts")
    public String orderProducts(@RequestParam Integer orderId,
                                Model model) {
        model.addAttribute("allProducts", orderProductsService.allOrderProducts(orderId));
        return "order_products_page";
    }
    @GetMapping(path = "/first")
    public String homePage(){
        return "first_page";
    }
}
