package com.banana.mycrm.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/users", produces="application/json")
@CrossOrigin(origins="*")
public class CustomerController {

}
