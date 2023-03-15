package oneeight.shop.repository;

import oneeight.shop.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
    List<Feedback> findAllByProductAndPosted  (Product productId, Boolean posted);
    Feedback findAllByAppUserAndProduct(AppUser appUser, Product product);
    Feedback findFeedbackById(Integer feedbackId);
    List<Feedback> findFeedbacksByAppUser(AppUser appUser);


    List<Feedback> findAllByProductId(Integer productId);
}
