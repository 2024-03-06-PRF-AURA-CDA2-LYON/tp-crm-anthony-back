package com.banana.mycrm.controller;

import com.banana.mycrm.entity.Customer;
import com.banana.mycrm.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path="/customers", produces="application/json")
@CrossOrigin(origins="http://localhost:4200")
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @GetMapping("")
    public Iterable<Customer> getCustomers(){
        return  this.customerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Customer> getCustomer(@PathVariable("id") Long id){
        return  this.customerRepository.findById(id);
    }

    @PostMapping("")
    public ResponseEntity<String>  addCustomer(@RequestBody Customer customer){
        try{
            this.customerRepository.save(customer);
            return ResponseEntity.status(HttpStatus.CREATED).body("Customer added successfully.");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding customer:" + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer){
        Optional<Customer> updateCustomerOpt =  this.customerRepository.findById(id);

        if (updateCustomerOpt.isPresent()) {
            Customer updateCustomer = updateCustomerOpt.get();

            updateCustomer.setAddress(customer.getAddress());
            updateCustomer.setCity(customer.getCity());
            updateCustomer.setCompanyName(customer.getCompanyName());
            updateCustomer.setCountry(customer.getCountry());
            updateCustomer.setEmail(customer.getEmail());
            updateCustomer.setFirstName(customer.getFirstName());
            updateCustomer.setLastName(customer.getLastName());
            updateCustomer.setPhoneNumber(customer.getPhoneNumber());
            updateCustomer.setState(customer.getState());
            updateCustomer.setZipCode(customer.getZipCode());

            this.customerRepository.save(updateCustomer);

            return ResponseEntity.ok("Customer updated successfully.");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") Long id){
        try{
            this.customerRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Customer deleted successfully.");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting customer:" + e.getMessage());
        }

    }
}
