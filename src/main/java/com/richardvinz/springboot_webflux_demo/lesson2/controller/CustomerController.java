package com.richardvinz.springboot_webflux_demo.lesson2.controller;

import com.richardvinz.springboot_webflux_demo.lesson2.dto.Customer;
import com.richardvinz.springboot_webflux_demo.lesson2.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>>getCustomerList(){
        return new ResponseEntity<>(customerService.loadAllCustomer(), HttpStatus.OK);
    }
    @GetMapping(value = "/stream",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<Flux<Customer>>getCustomerStream(){
        return new ResponseEntity<>(customerService.loadAllCustomerStream(), HttpStatus.OK);
    }
}
