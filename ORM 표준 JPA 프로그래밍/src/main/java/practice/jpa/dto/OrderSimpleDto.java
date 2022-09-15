package practice.jpa.dto;

import lombok.Data;
import practice.jpa.domain.Address;
import practice.jpa.domain.Order;
import practice.jpa.domain.OrderStatus;

import java.time.LocalDate;

@Data
public class OrderSimpleDto {
    private Long orderId;
    private String name;
    private LocalDate orderDate;
    private OrderStatus orderStatus;
    private Address address;

    public OrderSimpleDto(Order order) {
        orderId = order.getId();
        name = order.getCustomer().getName();
        orderDate = order.getOrderDate();
        orderStatus = order.getOrderStatus();
        address = order.getCustomer().getAddress();
    }
}
