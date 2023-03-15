package oneeight.shop.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table (name = "users")
public class User {

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
}
