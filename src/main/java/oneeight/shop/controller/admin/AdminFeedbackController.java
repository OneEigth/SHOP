package oneeight.shop.controller.admin;

import lombok.RequiredArgsConstructor;
import oneeight.shop.entity.Feedback;
import oneeight.shop.service.FeedbackService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/admin/feedbacks")
public class AdminFeedbackController {

    private final FeedbackService feedbackService;

    @GetMapping
    public String feedbacksModeration(Model model) {
        model.addAttribute("feedbacks", feedbackService.allFeedbacks());
        return "admin_feedbacks_mod_page";
    }

    @GetMapping("/feedbackMod")
    public String feedbacksModerationAction(@RequestParam Integer feedbackId) {
        feedbackService.feedbackModeration(feedbackId);
        return "redirect:/admin/feedbacks";
    }

    @GetMapping(path = "/feedbackDel")
    public String feedbackDeleteAction(@RequestParam Integer feedbackId) {
        feedbackService.feedbackDelete(feedbackId);
        return "redirect:/admin/feedbacks";
    }

}
