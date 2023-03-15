package oneeight.shop.service;

import lombok.RequiredArgsConstructor;
import oneeight.shop.entity.*;
import oneeight.shop.notification.OrderNotificationService;
import oneeight.shop.repository.CartItemsRepository;
import oneeight.shop.repository.OrderProductRepository;
import oneeight.shop.repository.OrderRepository;
import oneeight.shop.repository.StatusRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartItemsRepository cartItemsRepository;
    private final OrderProductRepository orderProductRepository;
    private final StatusRepository statusRepository;
    private final OrderNotificationService orderNotificationService;

    public List<Order> allOrdersByUser(AppUser appUser) {
        return orderRepository.findAllByAppUser(appUser);
    }
    public List<Order> allOrders(){return orderRepository.findAll();}

    public void deleteOrder(Integer orderId) {
        List<Order_product>order_products=orderProductRepository.findAllByOrderId(orderId);
        for(Order_product product:order_products){
            orderProductRepository.delete(product);
        }
        orderRepository.delete(orderRepository.findOrderById(orderId));
    }

    public void createOrder(AppUser appUser, String adres, Status status) {
        Order order = new Order();
        order.setDateReg(LocalDateTime.now());
        order.setAppUser(appUser);
        order.setDeliveryAdress(adres);
        order.setStatus(status);
        orderRepository.save(order);

        List<Cart_items> cart_items = cartItemsRepository.findCart_itemsByUser(appUser);
        for (Cart_items cartItem : cart_items) {
            Order_product order_product = new Order_product();
            order_product.setProduct(cartItem.getProduct());
            order_product.setQuantity(cartItem.getQuantity());
            order_product.setOrder(order);
            orderProductRepository.save(order_product);
            cartItemsRepository.delete(cartItem);
        }
    }

    public void setOrderStatus(Integer orderId, Integer statusId) {
        Order order = orderRepository.findOrderById(orderId);
        order.setStatus(statusRepository.findStatusById(statusId));
        orderRepository.save(order);
        orderNotificationService.sendOrderNotification(order);
    }

}
