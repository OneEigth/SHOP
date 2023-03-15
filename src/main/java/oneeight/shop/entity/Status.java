package oneeight.shop.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table (name = "statuses")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String status;

    @OneToMany(mappedBy = "status")
    private List<Order> orders;
}
