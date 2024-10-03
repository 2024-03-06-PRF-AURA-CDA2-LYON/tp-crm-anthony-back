package com.banana.mycrm.controller;

import com.banana.mycrm.entity.Customer;
import com.banana.mycrm.repository.CustomerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path="/customers", produces="application/json")
@CrossOrigin(origins="localhost")
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
    public void addCustomer(@RequestBody Customer customer){
        this.customerRepository.save(customer);
    }

    @PatchMapping("/{id}")
    public void updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer){
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

            this.customerRepository.save(customer);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable("id") Long id){
        this.customerRepository.deleteById(id);
    }
}
