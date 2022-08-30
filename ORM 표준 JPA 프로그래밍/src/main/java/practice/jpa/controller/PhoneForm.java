package practice.jpa.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class PhoneForm {

    @NotEmpty(message = "이름을 입력해주세요")
    private String name;

    private int price;
    private int stockQuantity;

    private String company;

}
