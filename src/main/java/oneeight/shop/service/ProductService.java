package oneeight.shop.service;

import lombok.RequiredArgsConstructor;
import oneeight.shop.entity.AppUser;
import oneeight.shop.entity.Feedback;
import oneeight.shop.entity.Option;
import oneeight.shop.entity.Product;
import oneeight.shop.exception.RecordNotFoundException;
import oneeight.shop.repository.CharacteristicRepository;
import oneeight.shop.repository.FeedbackRepository;
import oneeight.shop.repository.OptionRepository;
import oneeight.shop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final OptionRepository optionRepository;
    private final CharacteristicRepository characteristicRepository;
    private final FeedbackRepository feedbackRepository;
    private final UserService userService;

    public void createProduct(Product product, Map<Integer, String> charToValue) {
        productRepository.save(product);

        Set<Integer> chId = charToValue.keySet();
        for (Integer key : chId) {
            Option option = new Option();
            option.setCharacteristic(characteristicRepository.findById(key).get());
            option.setOption(charToValue.get(key));
            option.setProduct(product);
            optionRepository.save(option);
        }

    }

    public Product getProduct(Integer productId) {
        return productRepository.findById(productId).orElseThrow(() -> {
            String message = "'Product' not found by 'productId': %s".formatted(productId);
            throw new RecordNotFoundException(message);
        });
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void deleteProduct(Integer productId) {
        Product product = productRepository.findProductById(productId);
        List<Feedback> feedbackList = feedbackRepository.findAllByProductId(productId);
        feedbackRepository.deleteAll(feedbackList);
        productRepository.delete(product);
    }

    public List<Option> getOptions(Product productId) {
        return optionRepository.findAllByProduct(productId);
    }

    public List<Feedback> getFeedbacks(Product productId, Boolean posted) {
        return feedbackRepository.findAllByProductAndPosted(productId, posted);
    }

    public Integer ratingProduct(Product product, Boolean posted) {
        List<Feedback> feedbackList = feedbackRepository.findAllByProductAndPosted(product, posted);
        Integer rating = 0;
        int ratings = 0;
        int i = 0;
        for (Feedback feedback : feedbackList) {
            i = i + 1;
            ratings = ratings + feedback.getEstimate();
        }
        if (ratings != 0) {
            rating = ratings / i;
        } else
            rating = 0;

        return rating;
    }

    public void createFeedback(Product productId, String text, Integer estimate) {
        Feedback feedback = new Feedback();
        AppUser currentUser = userService.getCurrentUser();
        if (feedbackRepository.findAllByAppUserAndProduct(currentUser, productId) == null) {
            feedback.setProduct(productId);
            feedback.setDatereg(LocalDateTime.now());
            feedback.setEstimate(estimate);
            feedback.setPosted(false);
            feedback.setAppUser(currentUser);
            feedback.setText(text);
            feedbackRepository.save(feedback);
        }
    }

    public Boolean postedUser(Product product) {
        AppUser currentUser = userService.getCurrentUser();
        Boolean posted = false;
        if (feedbackRepository.findAllByAppUserAndProduct(currentUser, product) != null) {
            posted = true;
        }
        return posted;
    }

}

