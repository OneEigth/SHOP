package oneeight.shop.repository;

import oneeight.shop.entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValueRepository extends JpaRepository<Option, Integer> {

    //
}
