package oneeight.shop.service;

import lombok.RequiredArgsConstructor;
import oneeight.shop.entity.Order_product;
import oneeight.shop.repository.OrderProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
@RequestMapping
public class OrderProductsService {
    private final OrderProductRepository orderProductRepository;

    public List<Order_product> allOrderProducts(Integer orderId){
        return orderProductRepository.findAllByOrderId(orderId);
    }
}
