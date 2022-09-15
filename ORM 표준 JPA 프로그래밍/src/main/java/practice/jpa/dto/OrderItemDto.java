package practice.jpa.dto;

import lombok.Data;
import practice.jpa.domain.OrderItem;

@Data
public class OrderItemDto {
    private String item;
    private int orderPrice;
    private int count;

    public OrderItemDto(OrderItem orderItem) {
        item = orderItem.getItem().getName();
        orderPrice = orderItem.getOrderPrice();
        count = orderItem.getCount();
    }
}
