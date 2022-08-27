package practice.jpa.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Setter @Getter
public class CustomerForm {

    @NotEmpty
    private String name;

    private String palDo;
    private String dong;
    private String street;
}
