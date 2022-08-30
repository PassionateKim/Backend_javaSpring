package practice.jpa.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Setter @Getter
public class CustomerForm {

    @NotEmpty(message = "이름을 입력해주세요")
    private String name;

    @NotEmpty(message = "도를 입력해주세요")
    private String palDo;

    @NotEmpty(message = "동을 입력해주세요")
    private String dong;

    @NotEmpty(message = "도로를 입력해주세요")
    private String street;
}
