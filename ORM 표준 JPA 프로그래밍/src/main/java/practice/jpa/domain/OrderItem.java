package practice.jpa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Setter @Getter
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "ORDER_ID")
    @JsonIgnore
    private Order order;

    protected OrderItem(){
    }
    private int orderPrice;

    private int count;


    /**
     * 생성 로직
     */
    public static OrderItem createOrderItem(Item item, int price, int count){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(price);
        orderItem.setCount(count);

        item.removeQuantity(count);
        return orderItem;
    }

    public void cancel() {
        getItem().addQuantity(count);
    }

    // 조회로직
    public int getTotalPrice() {
        return orderPrice * count;
    }

}
