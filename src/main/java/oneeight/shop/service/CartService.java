package oneeight.shop.service;

import lombok.RequiredArgsConstructor;
import oneeight.shop.entity.AppUser;
import oneeight.shop.entity.Cart_items;
import oneeight.shop.repository.CartItemsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartItemsRepository cartItemsRepository;
    private final UserService userService;
    private final ProductService productService;


    public List<Cart_items> getCartItemsByCurrentUser() {
        List<Cart_items> cartItems = cartItemsRepository.findAllByUser(userService.getCurrentUser());
        return cartItems;
    }

    public void addProduct(int productId) {
        AppUser currentUser = userService.getCurrentUser();
        Cart_items cartItem = cartItemsRepository.findCart_itemsByUserAndProductId(currentUser, productId);
        if (cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            cartItemsRepository.save(cartItem);
        } else {
            Cart_items cartItems = new Cart_items();
            cartItems.setProduct(productService.getProduct(productId));
            cartItems.setUser(userService.getCurrentUser());
            cartItems.setQuantity(1);
            cartItemsRepository.save(cartItems);
            System.out.println(userService.getCurrentUser());
        }
    }

    public void increaseQuantity(int productId) {
        AppUser currentUser = userService.getCurrentUser();
        Cart_items cartItems = cartItemsRepository.findCart_itemsByUserAndProductId(currentUser, productId);
        cartItems.setQuantity(cartItems.getQuantity() + 1);
        cartItemsRepository.save(cartItems);
    }

    public void decreaseQuantity(int productId) {
        AppUser currentUser = userService.getCurrentUser();
        Cart_items cartItems = cartItemsRepository.findCart_itemsByUserAndProductId(currentUser, productId);
        cartItems.setQuantity(cartItems.getQuantity() - 1);
        if (cartItems.getQuantity() == 0) {
            cartItemsRepository.delete(cartItems);
        } else {
            cartItemsRepository.save(cartItems);
        }
    }

    public void deleteProduct(int productId) {
        AppUser currentUser = userService.getCurrentUser();
        Cart_items cartItems = cartItemsRepository.findCart_itemsByUserAndProductId(currentUser, productId);
        cartItemsRepository.delete(cartItems);
    }

    public void deleteAllProducts() {
        AppUser currentUser = userService.getCurrentUser();
        List<Cart_items> cartItems = cartItemsRepository.findCart_itemsByUser(currentUser);
        cartItemsRepository.deleteAll(cartItems);
    }

    public Double totalPriceCart(){
        List<Cart_items>cart_items=getCartItemsByCurrentUser();
        double totalPrice = 0;
        for (Cart_items cartItem:cart_items){
            double sum = cartItem.getQuantity() * cartItem.getProduct().getPrice();
            totalPrice = totalPrice + sum;
        }
        return totalPrice;
    }

}





