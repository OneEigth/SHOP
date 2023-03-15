package oneeight.shop.controller;

import lombok.RequiredArgsConstructor;
import oneeight.shop.entity.Order;
import oneeight.shop.service.CartService;
import oneeight.shop.service.OrderService;
import oneeight.shop.service.StatusService;
import oneeight.shop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/orders")
public class OrderController {

    private final StatusService statusService;
    private final OrderService orderService;
    private final UserService userService;
    private final CartService cartService;




    @GetMapping(path = "/formedOrder")
    public String formedOrder(Model model) {
        model.addAttribute("cartItems", cartService.getCartItemsByCurrentUser());
        model.addAttribute("totalPrice", cartService.totalPriceCart());
        return "create_order_page_new";
    }

    @GetMapping(path = "/createOrder")
    public String createOrderAction(@RequestParam String adres) {
        orderService.createOrder(userService.getCurrentUser(), adres, statusService.statusFormed());
        System.out.println(statusService.statusFormed().getStatus());
        return "redirect:/filter";

    }

}
