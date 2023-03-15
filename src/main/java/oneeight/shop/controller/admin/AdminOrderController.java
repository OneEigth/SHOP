package oneeight.shop.controller.admin;

import lombok.RequiredArgsConstructor;
import oneeight.shop.service.OrderProductsService;
import oneeight.shop.service.OrderService;
import oneeight.shop.service.StatusService;
import oneeight.shop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/admin/orders")
public class AdminOrderController {
    private final OrderService orderService;
    private final StatusService statusService;
    private final OrderProductsService orderProductsService;

    @GetMapping
    public String deleteOrder(Model model) {
        model.addAttribute("orders", orderService.allOrders());
        model.addAttribute("allStatuses", statusService.allStatus());
        return "admin_orders_page";
    }

    @GetMapping(path = "/deleteOder")
    public String deleteOrder(@RequestParam Integer orderId) {
        orderService.deleteOrder(orderId);
        return "redirect:/admin/orders";
    }

    @GetMapping(path = "/setOrderStatus")
    public String setStatus(@RequestParam Integer orderId,
                            @RequestParam Integer statusId) {

        orderService.setOrderStatus(orderId, statusId);
        return "redirect:/admin/orders";
    }
    @GetMapping(path = "/orderProducts")
    public String orderProducts(@RequestParam Integer orderId,
                                Model model) {
        model.addAttribute("allProducts", orderProductsService.allOrderProducts(orderId));
        return "admin_order_products_page";
    }
}
