package oneeight.shop.repository;

import oneeight.shop.entity.Option;
import oneeight.shop.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByServiceName(String serviceName);
}
