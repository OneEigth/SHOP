package oneeight.shop.repository;

import oneeight.shop.entity.Category;
import oneeight.shop.entity.Characteristic;
import oneeight.shop.entity.Product;
import oneeight.shop.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CharacteristicRepository extends JpaRepository<Characteristic, Integer> {

    List<Characteristic> findAllByCategory(Category categoryId);
}
