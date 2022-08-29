package practice.jpa.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import practice.jpa.domain.Address;
import practice.jpa.domain.Customer;
import practice.jpa.service.CustomerService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CustomerController {
    @Autowired
    private final CustomerService customerService;

    @GetMapping("/customers/new")
    public String signUpForm(Model model){
        model.addAttribute("customerForm", new CustomerForm());

        return "customers/createCustomerForm";
    }

    @PostMapping("/customers/new")
    public String signUp(@Valid CustomerForm customerForm, BindingResult result){
        if(result.hasErrors()){
            return "customers/createCustomerForm";
        }

        //address 생성
        Address address = new Address(customerForm.getPalDo(), customerForm.getDong(), customerForm.getStreet());

        // customer 생성
        Customer customer = new Customer();
        customer.setName(customerForm.getName());
        customer.setAddress(address);

        // customer 저장
        customerService.join(customer);

        return "redirect:/";
    }

    @GetMapping("/customers")
    public String list(Model model){
        List<Customer> customers = customerService.findCustomers();
        model.addAttribute("customers", customers);

        return "customers/customerList";
    }
}
