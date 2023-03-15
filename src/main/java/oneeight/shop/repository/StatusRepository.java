package oneeight.shop.repository;

import oneeight.shop.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status,Integer> {
    Status findAllByStatus(String status);
    Status findStatusById(Integer statusId);
}
