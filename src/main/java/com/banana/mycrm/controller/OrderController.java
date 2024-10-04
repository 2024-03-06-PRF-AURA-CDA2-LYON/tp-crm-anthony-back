package com.banana.mycrm.controller;

import com.banana.mycrm.entity.Customer;
import com.banana.mycrm.entity.Order;
import com.banana.mycrm.repository.CustomerRepository;
import com.banana.mycrm.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path="/orders", produces="application/json")
@CrossOrigin(origins="http://localhost:4200")
public class OrderController {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public OrderController(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    @GetMapping("")
    public Iterable<Order> getOrders(){
        return this.orderRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Order> getOrder(@PathVariable("id") Long id){
        return  this.orderRepository.findById(id);
    }

    @PostMapping("")
    public ResponseEntity<Void> addOrder(@RequestBody Order order){
        try{
            this.orderRepository.save(order);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateOrder(@PathVariable("id") Long id, @RequestBody Order order){
        Optional<Order> updateOrderOpt =  this.orderRepository.findById(id);

        if (updateOrderOpt.isPresent()) {
            Order updateOrder = updateOrderOpt.get();

            updateOrder.setTva(order.getTva());
            updateOrder.setComment(order.getComment());
            updateOrder.setNbDays(order.getNbDays());
            updateOrder.setServiceType(order.getServiceType());
            updateOrder.setState(order.getState());
            updateOrder.setTotalExcludeTax(order.getTotalExcludeTax());
            if (order.getClientId() != null) {
                Optional<Customer> customerOpt =  this.customerRepository.findById(order.getClientId().getId());
                if (customerOpt.isPresent()) {
                    Customer customer = customerOpt.get();
                    updateOrder.setClientId(customer);
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                }
            }

            this.orderRepository.save(updateOrder);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("id") Long id){
        try{
            this.orderRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
