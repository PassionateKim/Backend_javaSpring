package practice.jpa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import practice.jpa.domain.Customer;
import practice.jpa.domain.Item;
import practice.jpa.domain.Order;
import practice.jpa.service.CustomerService;
import practice.jpa.service.ItemService;
import practice.jpa.service.OrderService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final ItemService itemService;
    private final CustomerService customerService;

    @GetMapping("/order")
    public String createOrderForm(Model model){
        List<Item> items = itemService.findItems();
        List<Customer> customers = customerService.findCustomers();

        model.addAttribute("customers", customers);
        model.addAttribute("items", items);

        return "order/orderForm";
    }

    @PostMapping("/order")
    public String order(@RequestParam("customerId") Long customerId,
                        @RequestParam("itemId") Long itemId,
                        @RequestParam("count") int count){

        orderService.order(customerId, itemId, count);

        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String orderList(Model model){
        List<Order> orders = orderService.findOrders();
        model.addAttribute("orders", orders);

        return "order/orderList";
    }
}
