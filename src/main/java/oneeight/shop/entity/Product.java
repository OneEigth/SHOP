package oneeight.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String name;

    private Double price;

    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    private List<Option> options;

    @JsonIgnore
    @OneToMany (mappedBy = "product")
    private List<Order_product> orderProducts;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private  List<Cart_items> cartItems;
}


