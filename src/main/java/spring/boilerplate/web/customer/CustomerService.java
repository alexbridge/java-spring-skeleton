package spring.boilerplate.web.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.boilerplate.customer.Customer;
import spring.boilerplate.customer.CustomerRepository;

import java.util.List;

@Component("customerService")
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }

    public Customer getById(String id) {
        Customer customer = repository.findOne(Long.parseLong(id));
        if (customer == null) {
            throw new CustomerNotFoundException(id);
        }
        return customer;
    }

    public Customer addCustomer(Customer customer) {
        return repository.save(customer);
    }

    public Customer updateCustomer(String id, Customer customer) {
        boolean exist = repository.exists(Long.parseLong(id));
        if (!exist) {
            throw new CustomerNotFoundException(id);
        }
        customer.setId(Long.parseLong(id));
        return repository.save(customer);
    }

    public void deleteCustomer(String id) {
        boolean exist = repository.exists(Long.parseLong(id));
        if (!exist) {
            throw new CustomerNotFoundException(id);
        }
        repository.delete(Long.parseLong(id));
    }
}
