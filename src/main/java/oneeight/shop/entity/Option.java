package oneeight.shop.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@Entity
@Table(name = "options")
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "products_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "characteristics_id")
    private Characteristic characteristic;

    private String option;


}
