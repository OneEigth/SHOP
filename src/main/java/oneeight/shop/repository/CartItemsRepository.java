package oneeight.shop.repository;

import oneeight.shop.entity.AppUser;
import oneeight.shop.entity.Cart_items;
import oneeight.shop.service.UserService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemsRepository extends JpaRepository<Cart_items, Integer> {
    List<Cart_items> findAllByUser(AppUser user);

    Cart_items findCart_itemsByUserAndProductId(AppUser appUser,int productId);
    List<Cart_items> findCart_itemsByUser(AppUser appUser);
}
