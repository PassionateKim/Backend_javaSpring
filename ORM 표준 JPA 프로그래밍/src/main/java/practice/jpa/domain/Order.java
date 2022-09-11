package practice.jpa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Table(name = "orders")
@Setter @Getter
public class Order {

    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    private LocalDate orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    // 양방향 연관관계 편의 메서드
    public void setCustomer(Customer customer){
        customer.getOrders().add(this);
        this.customer = customer;
    }

    public void addOrderItem(OrderItem orderItem){
        orderItem.setOrder(this);
        orderItems.add(orderItem);
    }

    //비지니스 로직//
    /**
     * order 생성
     */
    public static Order createOrder(Customer customer, OrderItem... orderItems){
        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDate(LocalDate.now());
        order.setOrderStatus(OrderStatus.ORDER); // 초기값은 ORDER
        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }

        return order;
    }

    /**
     * 주문 취소
     */
    public void cancel(){
        this.setOrderStatus(OrderStatus.CANCEL);
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }

    // 조회 로직
    /**
     * 전체 주문 가격
     */
    public int getTotalPrice(){
        int sum = 0;
        for (OrderItem orderItem : orderItems) {
            sum += orderItem.getTotalPrice();
        }

        return sum;
    }
}
