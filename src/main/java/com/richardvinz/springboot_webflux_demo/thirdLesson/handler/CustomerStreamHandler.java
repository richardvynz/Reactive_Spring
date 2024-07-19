package com.richardvinz.springboot_webflux_demo.thirdLesson.handler;

import com.richardvinz.springboot_webflux_demo.lesson2.dao.CustomerDao;
import com.richardvinz.springboot_webflux_demo.lesson2.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerStreamHandler {

    @Autowired
    private CustomerDao customerDao;

    public Mono<ServerResponse>getCustomers(ServerRequest request){
        Flux<Customer> customersStream = customerDao.getCustomersStream();
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(customersStream, Customer.class);
    }
}
