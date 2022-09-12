package practice.jpa.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import practice.jpa.domain.Order;
import practice.jpa.dto.OrderDto;
import practice.jpa.repository.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {
    private final OrderRepository orderRepository;

    @GetMapping("/api/simple-orders1")
    public List<Order> orderList1(){
        List<Order> orderList = orderRepository.findAll();

        return orderList;
    }

    @GetMapping("/api/simple-orders2")
    public OrderResult orderList2(){
        List<Order> orderList = orderRepository.findAll();
        List<OrderDto> orderDtoList = orderList.stream()
                .map(o -> new OrderDto(o))
                .collect(Collectors.toList());

        return new OrderResult(orderDtoList);
    }

    @Data
    @AllArgsConstructor
    static class OrderResult<T>{
        private T orderDto;
    }

}
