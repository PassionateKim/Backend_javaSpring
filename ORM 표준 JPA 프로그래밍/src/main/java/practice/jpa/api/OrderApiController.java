package practice.jpa.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import practice.jpa.domain.Order;
import practice.jpa.domain.OrderItem;
import practice.jpa.repository.OrderRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderApiController {
    private final OrderRepository orderRepository;

    @GetMapping("/api/orders1")
    public List<Order> orders(){
        List<Order> orderList = orderRepository.findAll();
        for (Order order : orderList) {
            order.getCustomer().getName(); // 강제 LAZY 로딩
            List<OrderItem> orderItems = order.getOrderItems(); // 강제 LAZY 로딩
            orderItems.stream()
                    .forEach(o -> o.getItem().getPrice()); // 강제 LAZY 로딩
        }

        return orderList;
    }
}
