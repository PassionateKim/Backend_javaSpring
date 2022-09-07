package practice.jpa.dto;

import lombok.Data;
import practice.jpa.domain.Address;
import practice.jpa.domain.Customer;

import javax.persistence.Embedded;

@Data
public class CreateCustomerDto {
    private String name;

    @Embedded
    private Address address;

    public Customer toEntity() {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setAddress(address);

        return customer;
    }
}
