package oneeight.shop.controller;

import lombok.RequiredArgsConstructor;
import oneeight.shop.entity.Cart_items;
import oneeight.shop.repository.ProductRepository;
import oneeight.shop.service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/cart")
public class CartController {

    private final CartService cartService;

    @GetMapping
    public String cartPage(Model model) {
        List<Cart_items> cartItems = cartService.getCartItemsByCurrentUser();
        double totalPrice = 0;
        for (Cart_items carts : cartItems) {
            double sum = carts.getQuantity() * carts.getProduct().getPrice();
            totalPrice = totalPrice + sum;
        }
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("cartItems", cartItems);
        return "basket_page";
    }

    @GetMapping(path = "/add")
    public String addProductAction(@RequestParam Integer productId) {
        cartService.addProduct(productId);
        return "redirect:/cart";
    }

    @GetMapping(path = "/increase_count")
    public String increaseCountAction(@RequestParam Integer productId) {
        cartService.increaseQuantity(productId);
        return "redirect:/cart";
    }

    @GetMapping(path = "/decrease_count")
    public String decreaseCountAction(@RequestParam Integer productId) {
        cartService.decreaseQuantity(productId);
        return "redirect:/cart";
    }

    @GetMapping(path = "/delete")
    public String deleteProductAction(@RequestParam(required = false) Integer productId) {
        if (productId == null) {
            cartService.deleteAllProducts();
        } else {
            cartService.deleteProduct(productId);
        }
        return "redirect:/cart";
    }
}
