package oneeight.shop.repository.specification;

import oneeight.shop.entity.Category;
import oneeight.shop.entity.Product;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ProductSpecification {

    public static Specification<Product> byPrice(String nameFragment, Integer categoryId, Integer from, Integer to) {
        return new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                if (nameFragment != null) {
                    predicates.add(criteriaBuilder.like(root.get("name"), "%" + nameFragment + "%"));
                }

                if (categoryId != null) {
                    predicates.add(criteriaBuilder.equal(root.get("category").get("id"), categoryId));
                }

                if (from != null) {
                    predicates.add(criteriaBuilder.greaterThan(root.get("price"), from));
                }

                if (to != null) {
                    predicates.add(criteriaBuilder.lessThan(root.get("price"), to));
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
        };
    }


}

