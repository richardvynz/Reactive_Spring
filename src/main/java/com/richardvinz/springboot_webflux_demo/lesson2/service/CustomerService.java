package com.richardvinz.springboot_webflux_demo.lesson2.service;


import com.richardvinz.springboot_webflux_demo.lesson2.dao.CustomerDao;
import com.richardvinz.springboot_webflux_demo.lesson2.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerDao customerDao;

    public List<Customer>loadAllCustomer(){
        long start = System.currentTimeMillis();
        List<Customer>customerList = customerDao.getCustomers();
        long end = System.currentTimeMillis();
        System.out.println("Total execution time: " + (end-start));
        return customerList;
    }

    public Flux<Customer>loadAllCustomerStream(){
        long start = System.currentTimeMillis();
        Flux<Customer> customerList = customerDao.getCustomersStream();
        long end = System.currentTimeMillis();
        System.out.println("Total execution time for stream: " + (end-start));
        return customerList;
    }


}
