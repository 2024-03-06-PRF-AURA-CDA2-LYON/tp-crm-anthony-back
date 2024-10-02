package com.banana.mycrm.controller;

import com.banana.mycrm.entity.Customer;
import com.banana.mycrm.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/customers", produces="application/json")
@CrossOrigin(origins="*")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("")
    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Customer> getCustomer(@PathVariable("id") int id){
        return customerRepository.findById(id);
    }

    @PostMapping("")
    public void addCustomer(@RequestBody Customer customer){
        customerRepository.save(customer);
    }

    @PatchMapping("/{id}")
    public void updateCustomer(@PathVariable("id") int id, @RequestBody Customer customer){
        Customer updateCustomer = customerRepository.getReferenceById(id);
        updateCustomer.setAddress(customer.getAddress());
        updateCustomer.setCity(customer.getCity());
        updateCustomer.setCompany_name(customer.getCompany_name());
        updateCustomer.setCountry(customer.getCountry());
        updateCustomer.setEmail(customer.getEmail());
        updateCustomer.setFirst_name(customer.getFirst_name());
        updateCustomer.setLast_name(customer.getLast_name());
        updateCustomer.setPhone_number(customer.getPhone_number());
        updateCustomer.setState(customer.getState());
        updateCustomer.setZip_code(customer.getZip_code());
        customerRepository.save(customer);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable("id") int id){
        customerRepository.deleteById(id);
    }

}
