package practice.jpa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter @Getter
public class Customer {
    @Id @GeneratedValue
    @Column(name = "CUSTOMER_ID")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders = new ArrayList<>();

}
