package guru.springframework.spring6restmvc.controller;

import guru.springframework.spring6restmvc.model.Customer;
import guru.springframework.spring6restmvc.services.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<Void> createCustomer(@RequestBody Customer customer) {
        log.debug("Create Customer - in controller");

        Customer savedCustomer = customerService.createCustomer(customer);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, "/api/v1/customer/" + savedCustomer.getId());

        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).build();
    }

    @GetMapping
    public List<Customer> listCustomers() {
        log.debug("List Customers - in controller");
        return customerService.listCustomers();
    }

    @GetMapping("/{customerId}")
    public Customer getCustomerById(@PathVariable("customerId") UUID customerId) {
        log.debug("Get Customer by Id - in controller");
        return customerService.getCustomerById(customerId);
    }

}
