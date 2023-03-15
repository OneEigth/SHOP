package oneeight.shop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table (name = "users")
public class AppUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String login;

  private String password;

  private String name;

  private String surname;

  private String email;

  @Column (name = "date_reg")
  private LocalDateTime dateReg;

  @ManyToOne
  @JoinColumn (name="role_id")
  private Role role;

  @OneToMany(mappedBy = "appUser")
  private List<Order> orders;

  @OneToMany(mappedBy = "user")
  private List<Cart_items> cartItems;
}
