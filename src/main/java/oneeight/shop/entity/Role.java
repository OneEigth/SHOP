package oneeight.shop.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "display_name")
    private String displayName;

}
