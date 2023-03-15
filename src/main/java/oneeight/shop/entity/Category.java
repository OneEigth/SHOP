package oneeight.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Product> products;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Characteristic> characteristics;

   }
