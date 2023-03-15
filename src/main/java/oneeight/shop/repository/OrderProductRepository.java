package oneeight.shop.repository;

import oneeight.shop.entity.Order_product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderProductRepository extends JpaRepository<Order_product,Integer> {

        List<Order_product> findAllByOrderId(Integer orderId);
}

