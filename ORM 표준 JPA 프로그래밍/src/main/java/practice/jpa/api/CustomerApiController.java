package practice.jpa.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import practice.jpa.domain.Customer;
import practice.jpa.dto.CreateCustomerDto;
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

    @Data
    @AllArgsConstructor
    static class CreateCustomerResponse{
        private Long id;
    }
}
