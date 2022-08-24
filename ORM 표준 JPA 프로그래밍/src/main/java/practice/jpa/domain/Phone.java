package practice.jpa.domain;

import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Setter
public class Phone extends Item{

    private String company;
}
