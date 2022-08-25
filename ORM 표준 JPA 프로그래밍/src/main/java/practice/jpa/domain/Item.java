package practice.jpa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
public abstract class Item {
    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;
    private String name;

    private int price;

    private int stockQuantity;

    //==비지니스 로직==//

    /**
     * 증가
     */
    public void addQuantity(int quantity){
        this.stockQuantity += quantity;
    }

    /**
     * 감소
     */
    public void removeQuantity(int quantity){
        int rest = this.stockQuantity - quantity;
        if(rest < 0){
            throw new IllegalStateException("수량이 부족합니다.");
        }
        this.stockQuantity = rest;
    }
}
