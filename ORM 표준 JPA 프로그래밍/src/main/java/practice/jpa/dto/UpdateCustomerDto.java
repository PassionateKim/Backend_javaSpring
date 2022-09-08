package practice.jpa.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import practice.jpa.domain.Address;

import javax.persistence.Embedded;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@RequiredArgsConstructor
public class UpdateCustomerDto {

    @NotEmpty
    private String name;

    @Embedded
    @NotNull
    private Address address;

}
