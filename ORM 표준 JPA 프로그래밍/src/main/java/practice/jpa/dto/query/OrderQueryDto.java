package practice.jpa.dto.query;

import lombok.Data;
import practice.jpa.domain.Address;
import practice.jpa.domain.OrderStatus;

import java.time.LocalDate;
import java.util.List;

@Data
public class OrderQueryDto {
    private Long orderId;
    private String name;
    private LocalDate orderDate;
    private OrderStatus orderStatus;
    private Address address;
    private List<OrderQueryItemDto> orderItems;

    public OrderQueryDto(Long orderId, String name, LocalDate orderDate, OrderStatus orderStatus, Address address) {
        this.orderId = orderId;
        this.name = name;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.address = address;
    }
}
