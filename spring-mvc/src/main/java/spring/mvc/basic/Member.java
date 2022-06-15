package spring.mvc.basic;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Member {

    private Long id;
    private String name;
    private int age;

    public Member() {
    }

    public Member(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
