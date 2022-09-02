package practice.jpa.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class PhoneForm {
    private Long id;

    @NotEmpty(message = "이름을 입력해주세요")
    private String name;

    @NotNull(message = "가격을 입력해주세요")
    private Integer price;

    private int stockQuantity;

    @NotEmpty(message = "회사를 입력해주세요")
    private String company;

}
