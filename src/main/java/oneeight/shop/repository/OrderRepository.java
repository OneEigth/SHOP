package oneeight.shop.repository;

import oneeight.shop.entity.AppUser;
import oneeight.shop.entity.Order;
import oneeight.shop.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {
        List<Order> findAllByAppUser(AppUser appUser);
        List<Order> findAllByStatus(Status status);
        Order findOrderById(Integer id);

}
