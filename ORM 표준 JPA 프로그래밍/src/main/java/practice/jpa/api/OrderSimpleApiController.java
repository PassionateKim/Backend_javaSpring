package practice.jpa.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import practice.jpa.domain.Order;
import practice.jpa.repository.OrderRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {
    private final OrderRepository orderRepository;

    @GetMapping("/api/simple-orders1")
    public List<Order> orderList1(){
        List<Order> orderList = orderRepository.findAll();

        return orderList;
    }

}
