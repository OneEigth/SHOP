package oneeight.shop.service;

import lombok.RequiredArgsConstructor;
import oneeight.shop.entity.Category;
import oneeight.shop.entity.Role;
import oneeight.shop.exception.RecordNotFoundException;
import oneeight.shop.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    public final CategoryRepository categoryRepository;

    public Category getCategory(Integer categoryId){
        return categoryRepository.findById(categoryId).orElseThrow(() ->{
            String message = "'Category' not found by 'categoryId': %s".formatted(categoryId);
            throw new RecordNotFoundException(message);
        });
    }

}
