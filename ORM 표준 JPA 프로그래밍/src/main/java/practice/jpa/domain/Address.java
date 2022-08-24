package practice.jpa.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
    private String palDo;
    private String dong;
    private String street;
}
