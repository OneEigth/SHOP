package oneeight.shop.service;

import lombok.RequiredArgsConstructor;
import oneeight.shop.entity.AppUser;
import oneeight.shop.entity.Feedback;
import oneeight.shop.repository.FeedbackRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;

    public List<Feedback> allFeedbacks() {
        List<Feedback> feedbackList = feedbackRepository.findAll();
        return feedbackList;
    }

    public void feedbackModeration(Integer feedbackId) {
        Feedback feedbackMod = feedbackRepository.findFeedbackById(feedbackId);
        feedbackMod.setPosted(true);
        feedbackRepository.save(feedbackMod);
    }

    public void feedbackDelete(Integer feedbackId) {
        feedbackRepository.delete(feedbackRepository.findFeedbackById(feedbackId));
    }

    public List<Feedback> userFeedbacks(AppUser appUser){
        return feedbackRepository.findFeedbacksByAppUser(appUser);
    }

}
