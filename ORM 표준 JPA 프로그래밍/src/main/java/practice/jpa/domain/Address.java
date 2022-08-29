package practice.jpa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter @Setter
public class Address {
    private String palDo;
    private String dong;
    private String street;

    public Address() {
    }

    public Address(String palDo, String dong, String street) {
        this.palDo = palDo;
        this.dong = dong;
        this.street = street;
    }
}
