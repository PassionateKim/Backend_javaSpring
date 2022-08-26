package practice.jpa.domain;

import javax.persistence.Embeddable;

@Embeddable
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
