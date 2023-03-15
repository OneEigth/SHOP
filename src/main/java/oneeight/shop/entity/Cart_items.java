package oneeight.shop.entity;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "cart_items")
public class Cart_items {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;
}
