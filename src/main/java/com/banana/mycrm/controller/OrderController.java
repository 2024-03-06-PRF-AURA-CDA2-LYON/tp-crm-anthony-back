package com.banana.mycrm.controller;

import com.banana.mycrm.entity.Order;
import com.banana.mycrm.repository.OrderRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="/orders", produces="application/json")
@CrossOrigin(origins="localhost")
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("")
    public List<Order> getOrders(){
        return this.orderRepository.findAll();
    }
}
