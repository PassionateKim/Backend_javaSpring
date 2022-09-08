package practice.jpa.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import practice.jpa.domain.Address;
import practice.jpa.domain.Customer;
import practice.jpa.dto.CreateCustomerDto;
import practice.jpa.dto.UpdateCustomerDto;
import practice.jpa.service.CustomerService;

import javax.validation.Valid;

@Controller
@RestController
@RequiredArgsConstructor
public class CustomerApiController {
    private final CustomerService customerService;

    @PostMapping("/api/customer")
    public CreateCustomerResponse saveCustomer(@RequestBody @Valid CreateCustomerDto createCustomerDto){
        //Dto를 통해 생성
        Customer customer = createCustomerDto.toEntity();
        Long id = customerService.join(customer);

        return new CreateCustomerResponse(id);
    }

    @PatchMapping("/api/customer/{id}")
    public UpdateCustomerResponse updateCustomer(@PathVariable("id") Long customerId, @RequestBody @Valid UpdateCustomerDto updateCustomerDto){
        customerService.update(customerId, updateCustomerDto.getName(), updateCustomerDto.getAddress());
        Customer findCustomer = customerService.findOne(customerId);

        return new UpdateCustomerResponse(findCustomer.getId(), findCustomer.getName(), findCustomer.getAddress());
    }

    @Data
    @AllArgsConstructor
    static class CreateCustomerResponse{
        private Long id;
    }

    @Data
    @AllArgsConstructor
    static class UpdateCustomerResponse{
        private Long id;
        private String name;
        private Address address;

    }
}
