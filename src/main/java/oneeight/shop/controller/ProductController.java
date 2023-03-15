package oneeight.shop.controller;

import lombok.RequiredArgsConstructor;
import oneeight.shop.entity.Feedback;
import oneeight.shop.entity.Option;
import oneeight.shop.entity.Product;
import oneeight.shop.repository.CategoryRepository;
import oneeight.shop.repository.ProductRepository;
import oneeight.shop.repository.specification.ProductSpecification;
import oneeight.shop.service.ProductService;
import oneeight.shop.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductService productService;
    private final UserService userService;

    @GetMapping(path = "/create_product")
    public String createPage(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("category", categoryRepository.findAll());
        return "create_product_page";
    }


    @GetMapping(path = "/products")
    public String showProducts(@RequestParam(required = false) Double minPrice,
                               @RequestParam(required = false) Double maxPrice,
                               @RequestParam(required = false) Integer category,
                               Model model) {

        List<Product> filterProducts = new ArrayList<>();
        if (minPrice == null) {
            minPrice = Double.MIN_VALUE;
        }
        if (maxPrice == null) {
            maxPrice = Double.MAX_VALUE;
        }


        for (Product product : productRepository.findAll()) {
            if (category != 0 && !product.getCategory().getId().equals(category)) {
                continue;
            }
            if (product.getPrice() >= minPrice && product.getPrice() <= maxPrice) {
                filterProducts.add(product);
            }
        }
        model.addAttribute("categoryRep", categoryRepository.findAll());
        model.addAttribute("product", filterProducts);
        return "products_page";
    }


    @GetMapping(path = "/products/new")
    @ResponseBody
    public List<Product> productList(@RequestParam Double minPrice, @RequestParam Double maxPrice) {
        return productRepository.myGetByPrice(minPrice, maxPrice);
    }

    @GetMapping(path = "/products/sort")
    @ResponseBody
    public List<Product> sortedProductList() {
        Sort sort = Sort.by(
                Sort.Order.desc("price"),
                Sort.Order.asc("name")
        );
        return productRepository.findAll(sort);
    }

    @GetMapping(path = "/product/pageProductList")
    @ResponseBody
    public List<Product> pageProductList(@RequestParam(required = false) Integer page) {
        if (page == null) {
            page = 0;
        }
        Sort sort = Sort.by(
                Sort.Order.desc("price")
        );
        Pageable pageable = PageRequest.of(page, 3, sort);
        Page<Product> productPage = productRepository.findAll(pageable);
        return productPage.getContent();
    }


    @GetMapping(path = "/filter")
    public String filteredPruductList2(@RequestParam(required = false) String nameFragment,
                                       @RequestParam(required = false) Integer categoryId,
                                       @RequestParam(required = false) Integer from,
                                       @RequestParam(required = false) Product productId,
                                       @RequestParam(required = false) Integer to,
                                       Model model) {
        List<Product> filtered = productRepository.findAll(ProductSpecification.byPrice(nameFragment, categoryId, from, to));
        List<Option> optionList = productService.getOptions(productId);
        model.addAttribute("options", optionList);
        model.addAttribute("filtered", filtered);
        model.addAttribute("category", categoryRepository.findAll());
        model.addAttribute("isAdmin", userService.isAdmin());

        return "filter_page";
    }

    @GetMapping(path = "/char")
    public String charInfo(@RequestParam Product productId,
                           Model model) {
        List<Option> optionList = productService.getOptions(productId);
        List<Feedback> feedbackList = productService.getFeedbacks(productId, true);
        Product product = productService.getProduct(productId.getId());
        model.addAttribute("options", optionList);
        model.addAttribute("feedbacks", feedbackList);
        model.addAttribute("product", product);
        model.addAttribute("rating", productService.ratingProduct(productId, true));
        model.addAttribute("posted", productService.postedUser(productId));

        return "characteristics_page";
    }

    @GetMapping(path = "/feedback")
    public String feedback(@RequestParam Product productId,
                           @RequestParam String text,
                           @RequestParam Integer estimate
    ) {
        productService.createFeedback(productId, text, estimate);
        return "redirect:/filter";

    }


}
