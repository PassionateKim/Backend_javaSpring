package practice.jpa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Setter @Getter
public class Phone extends Item {

    private String company;

    public Phone() {
    }

    public Phone(String name, int price, int stockQuantity, String company) {
        super(name, price, stockQuantity);
        this.company = company;
    }
}
