package oneeight.shop.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Table(name = "feedbacks")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private AppUser appUser;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Boolean posted;

    private Integer estimate;

    @Column(name = "feedback")
    private String text;

    @Column(name = "date_red")
    private LocalDateTime datereg;

}
