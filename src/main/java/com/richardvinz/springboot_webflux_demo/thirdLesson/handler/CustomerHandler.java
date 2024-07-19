package com.richardvinz.springboot_webflux_demo.thirdLesson.handler;

import com.richardvinz.springboot_webflux_demo.lesson2.dao.CustomerDao;
import com.richardvinz.springboot_webflux_demo.lesson2.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {

    @Autowired
    private CustomerDao customerDao;

    public Mono<ServerResponse> loadCustomers(ServerRequest request){
        Flux<Customer> customerList = customerDao.getCustomerList();
        return ServerResponse.ok().body(customerList,Customer.class);
    }

    public Mono<ServerResponse> findCustomer(ServerRequest request){
      int customerId = Integer.valueOf(request.pathVariable("input"));
//                customerDao.getCustomerList().filter(customer -> customer.getId()  == customerId).take(1).single();
        Mono<Customer> customerMono = customerDao.getCustomerList().filter(customer -> customer.getId() == customerId).next();
        return ServerResponse.ok().body(customerMono, Customer.class);
    }

    public Mono<ServerResponse> saveCustomers(ServerRequest request) {
        Mono<Customer> customerMono = request.bodyToMono(Customer.class);
        Mono<String> savedResponse = customerMono.map(dto -> dto.getId() + " : " + dto.getName());
         return ServerResponse.ok().body(savedResponse,String.class);
    }
}
