package oneeight.shop.repository;

import oneeight.shop.entity.AppUser;
import oneeight.shop.entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    Optional<AppUser> findByLogin(String login);
}
