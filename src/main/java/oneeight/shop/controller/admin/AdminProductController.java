package oneeight.shop.controller.admin;

import oneeight.shop.entity.Product;
import oneeight.shop.repository.CategoryRepository;
import oneeight.shop.repository.ProductRepository;
import oneeight.shop.service.CategoryService;
import oneeight.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/admin/products")
public class AdminProductController {

    @Autowired
    private CategoryService categoryService;


    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryRepository categoryRepository;


    @GetMapping(path = "/allProducts")
    public String showProducts(Model model) {
        List<Product> allProducts = productService.getAllProducts();
        model.addAttribute("products", allProducts);
        return "admin_allProducts_page";
    }
    @GetMapping(path = "/deleteProduct")
    public String deleteProduct(@RequestParam Integer productId){
        productService.deleteProduct(productId);
        return "redirect:/allProducts";
    }

    @GetMapping(path = "/select_category")
    public String createPage(Model model) {
        model.addAttribute("category", categoryRepository.findAll());
        return "select_category_page";
    }
    @GetMapping(path = "/create")
    public String createCharacteristics(@RequestParam Integer categoryId, Model model) {
        Product product = new Product();
        product.setCategory(categoryService.getCategory(categoryId));
        model.addAttribute("product", product);
        return "create_product_page";
    }
    @PostMapping(path = "/create")
    public String createAction(@ModelAttribute Product product,
                               @RequestParam List<Integer> charId,
                               @RequestParam List<String> charValue) {
        System.out.println(product.getCategory());
        Map<Integer, String> charToValue = new HashMap<>();
        for (int i = 0; i < charId.size(); i++) {
            charToValue.put(charId.get(i), charValue.get(i));
        }
        productService.createProduct(product, charToValue);
        return "redirect:/admin";
    }
    @GetMapping(path = "/fedbackMod")
    public String feedbackModeration(){
        return "redirect:/filter";
    }


}
