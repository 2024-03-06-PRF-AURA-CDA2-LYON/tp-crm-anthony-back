package com.banana.mycrm.controller;

import com.banana.mycrm.entity.Order;
import com.banana.mycrm.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path="/orders", produces="application/json")
@CrossOrigin(origins="localhost")
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
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
    public void addOrder(@RequestBody Order order){
        this.orderRepository.save(order);
    }

    @PatchMapping("/{id}")
    public void updateOrder(@PathVariable("id") Long id, @RequestBody Order order){
        Optional<Order> updateOrderOpt =  this.orderRepository.findById(id);

        if (updateOrderOpt.isPresent()) {
            Order updateOrder = updateOrderOpt.get();

            updateOrder.setTVA(order.getTVA());
            updateOrder.setComment(order.getComment());
            updateOrder.setNbDays(order.getNbDays());
            updateOrder.setServiceType(order.getServiceType());
            updateOrder.setState(order.getState());
            updateOrder.setTotalExcludeTax(order.getTotalExcludeTax());
            updateOrder.setClient(order.getClient());

            this.orderRepository.save(order);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable("id") Long id){
        this.orderRepository.deleteById(id);
    }
}
