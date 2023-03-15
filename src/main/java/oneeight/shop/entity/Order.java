package oneeight.shop.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser appUser;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @Column (name="delivery_adress")
    private String deliveryAdress;

    @Column (name="date_reg")
    private LocalDateTime dateReg;

    @OneToMany (mappedBy = "order")
    private List<Order_product> orderProducts;


}
