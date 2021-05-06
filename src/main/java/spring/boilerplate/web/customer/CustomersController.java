package spring.boilerplate.web.customer;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spring.boilerplate.customer.Customer;

import java.util.List;

@RestController("Customers")
@RequestMapping("/customers")
@Api(value = "Customers", tags = "Customers")
public class CustomersController {

    @Autowired
    private CustomerService service;

    @RequestMapping(method= RequestMethod.GET)
    @Transactional
    public List<Customer> getAll() {
        return service.getAllCustomers();
    }

    @RequestMapping(path = "/{id}", method= RequestMethod.GET)
    public Customer get(@PathVariable String id) {
        return service.getById(id);
    }

    @RequestMapping(method= RequestMethod.POST)
    public Customer add(@RequestBody @Validated Customer customer) {
        return service.addCustomer(customer);
    }

    @RequestMapping(path = "/{id}", method= RequestMethod.PUT)
    public Customer update(@PathVariable String id, @RequestBody @Validated Customer customer) {
        return service.updateCustomer(id, customer);
    }

    @RequestMapping(path = "/{id}", method= RequestMethod.DELETE)
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        service.deleteCustomer(id);
    }
}
