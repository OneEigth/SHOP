package oneeight.shop.repository;

import oneeight.shop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.cdi.JpaRepositoryExtension;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {
    //List<Product> findAllByPriceBetween(Double from, Double to);

    @Query("select p from Product p where p.price between ?1 and ?2")
    List<Product> myGetByPrice(Double from, Double to);

    @Query("select p from Product p where p.category.id = ?1 and p.price between ?2 and ?3")
    List<Product> getFilterProducts(Integer categoryId, Double minPrice, Double maxPrice);

    Product findProductById(Integer productId);
}
