package spring.boilerplate.customer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component("customerRepository")
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Optional<Customer> findById(long id);

    Optional<Customer> findOneByEmail(String email);

    List<Customer> findAll();
}

