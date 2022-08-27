package practice.jpa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.jpa.domain.Customer;
import practice.jpa.repository.CustomerRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomerService {

    @Autowired
    private final CustomerRepository customerRepository;

    /**
     * 회원가입
     */
    @Transactional
    public Long join(Customer customer){
        validateCustomer(customer);
        customerRepository.save(customer);

        return customer.getId();
    }

    /**
     * 유효성 검증
     */
    private void validateCustomer(Customer customer){
        List<Customer> customerList = customerRepository.findByName(customer.getName());

        if(!(customerList.isEmpty())){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 회원 전체 조회
     */
    public List<Customer> findCustomers(){
        return customerRepository.findAll();
    }

    /**
     * 특정 Id의 회원 조회
     */
    public Customer findOne(Long id){
        return customerRepository.findOne(id);
    }
}
