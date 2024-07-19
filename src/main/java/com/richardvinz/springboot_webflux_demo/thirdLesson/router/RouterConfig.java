package com.richardvinz.springboot_webflux_demo.thirdLesson.router;

import com.richardvinz.springboot_webflux_demo.thirdLesson.handler.CustomerHandler;
import com.richardvinz.springboot_webflux_demo.thirdLesson.handler.CustomerStreamHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

    @Autowired
    private CustomerHandler handler;
    @Autowired
    private CustomerStreamHandler streamHandler;

    @Bean
    public RouterFunction<ServerResponse>routerFunction(){
         return RouterFunctions.route()
                 .GET("/router/customers",handler::loadCustomers)
                 .GET("/router/customers/stream",streamHandler::getCustomers )
                 .GET("/router/customers/stream/{input}",handler::findCustomer)
                 .POST("/router/customers/save",handler::saveCustomers)
                 .build();
    }
}
