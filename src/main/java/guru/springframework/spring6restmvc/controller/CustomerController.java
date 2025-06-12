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
public class CustomerController {

    public static final String CUSTOMER_PATH = "/api/v1/customer";
    public static final String CUSTOMER_PATH_ID = CUSTOMER_PATH + "/{customerId}";

    private final CustomerService customerService;

    @PatchMapping(CUSTOMER_PATH_ID)
    public ResponseEntity<Void> patchCustomerById(@PathVariable("customerId") UUID customerId,
                                                  @RequestBody Customer customer) {
        log.debug("Patch Customer - in controller");
        customerService.patchCustomerById(customerId, customer);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(CUSTOMER_PATH_ID)
    public ResponseEntity<Void> deleteCustomerById(@PathVariable("customerId") UUID customerId) {
        log.debug("Delete Customer by Id - in controller");
        customerService.deleteCustomerById(customerId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(CUSTOMER_PATH_ID)
    public ResponseEntity<Void> updateCustomerById(@PathVariable("customerId") UUID customerId,
                                                   @RequestBody Customer customer) {
        log.debug("Update Customer - in controller");
        customerService.updateCustomerById(customerId, customer);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(CUSTOMER_PATH)
    public ResponseEntity<Void> createCustomer(@RequestBody Customer customer) {
        log.debug("Create Customer - in controller");

        Customer savedCustomer = customerService.createCustomer(customer);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, CUSTOMER_PATH + "/" + savedCustomer.getId());

        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).build();
    }

    @GetMapping(CUSTOMER_PATH)
    public List<Customer> listCustomers() {
        log.debug("List Customers - in controller");
        return customerService.listCustomers();
    }

    @GetMapping(CUSTOMER_PATH_ID)
    public Customer getCustomerById(@PathVariable("customerId") UUID customerId) {
        log.debug("Get Customer by Id - in controller");
        return customerService.getCustomerById(customerId);
    }

}
