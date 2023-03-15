package oneeight.shop.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "order_products")
public class Order_product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private Integer quantity;
}
