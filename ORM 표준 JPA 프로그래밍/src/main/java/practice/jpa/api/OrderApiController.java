package practice.jpa.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import practice.jpa.domain.Order;
import practice.jpa.domain.OrderItem;
import practice.jpa.dto.OrderDto;
import practice.jpa.repository.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderApiController {
    private final OrderRepository orderRepository;

    @GetMapping("/api/orders1")
    public List<Order> orders1(){
        List<Order> orderList = orderRepository.findAll();
        for (Order order : orderList) {
            order.getCustomer().getName(); // 강제 LAZY 로딩
            List<OrderItem> orderItems = order.getOrderItems(); // 강제 LAZY 로딩
            orderItems.stream()
                    .forEach(o -> o.getItem().getPrice()); // 강제 LAZY 로딩
        }

        return orderList;
    }

    @GetMapping("/api/orders2")
    public Result orders2(){
        List<Order> orderList = orderRepository.findAll();

        List<OrderDto> orderDtoList = orderList.stream()
                .map(o -> new OrderDto(o))
                .collect(Collectors.toList());

        return new Result(orderDtoList);
    }

    @GetMapping("/api/orders3")
    public Result orders3(){
        List<Order> orderList = orderRepository.findAllByFetchOrders();
        orderList.stream()
                .map(o -> new OrderDto(o))
                .collect(Collectors.toList());

        return new Result(orderList);
    }

    /**
     * 페이징 문제 해결
     */
    @GetMapping("/api/orders4")
    public Result orders4(@RequestParam(value = "offset", defaultValue = "0") int offset,
                          @RequestParam(value = "limit", defaultValue = "100") int limit){
        List<Order> ordersWithToOne = orderRepository.findAllWithToOne(offset, limit);

        List<OrderDto> orderDtoList = ordersWithToOne.stream()
                .map(o -> new OrderDto(o))
                .collect(Collectors.toList());

        return new Result(orderDtoList);
    }


    @Data
    @AllArgsConstructor
    public class Result<T>{
        private T data;
    }
}
