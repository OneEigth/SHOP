package oneeight.shop.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@Getter
@Setter
@Entity
@Table(name = "characteristics")
public class Characteristic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String name;

    @OneToMany(mappedBy = "characteristic")
    private List<Option> options;


}
