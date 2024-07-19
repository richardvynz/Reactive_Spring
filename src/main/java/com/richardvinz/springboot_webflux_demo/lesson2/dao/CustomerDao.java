package com.richardvinz.springboot_webflux_demo.lesson2.dao;

import com.richardvinz.springboot_webflux_demo.lesson2.dto.Customer;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class CustomerDao {

    private static void sleepExecution(int i){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException exception){
            exception.printStackTrace();
        }
    }

    public List<Customer> getCustomers() {
        return IntStream.rangeClosed(1,15)
                .peek(CustomerDao::sleepExecution)
                .peek(i-> System.out.println("processing count: "+i))
                .mapToObj(i->new Customer(i,"customer: "+i))
                .collect(Collectors.toList());
    }

    public Flux<Customer> getCustomersStream(){
        return Flux.range(1,15)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i-> System.out.println("processing count in stream: "+i))
                .map(i->new Customer(i,"customer "+i));

    }

     public Flux<Customer> getCustomerList(){
        return Flux.range(1,50)
                .doOnNext(i-> System.out.println("processing count in stream flow: "+i))
                .map(i->new Customer(i,"customer "+i));

    }
}
