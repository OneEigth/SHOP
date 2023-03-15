package oneeight.shop.repository;

import oneeight.shop.entity.Option;
import oneeight.shop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionRepository extends JpaRepository<Option, Integer> {

    List<Option> findAllByProduct(Product productId);

}
