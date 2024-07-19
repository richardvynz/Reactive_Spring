package com.richardvinz.springboot_webflux_demo.firstLesson;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

class MonoFluxTestTest {

    @Test
    public void testMono(){
        Mono<String> monoString = Mono.just("vincent").log();
        monoString.subscribe(System.out::println);
    }

    @Test
    public void testMono_onError(){
        Mono<?> monoString = Mono.just("vincent")
                .then(Mono.error(new RuntimeException("onError"))).log();
        monoString.subscribe(System.out::println, ex->System.out.println(ex.getMessage()));
    }


    @Test
    public void testFlux(){
        Flux<String> stringFlux = Flux.just("vincent","richard","ndubuisi","emmanuel","uchechi")
                .onBackpressureBuffer().concatWithValues("AWS")
                .log();
        stringFlux.subscribe(System.out::println);
    }
    @Test
    public void testFlux_ErrorScenario(){
        Flux<String> stringFlux = Flux.just("vincent","richard","ndubuisi","emmanuel","uchechi")
                .concatWithValues("AWS")
                .concatWith(Flux.error(new RuntimeException("exception occurred in Flux ")))
                .concatWithValues("steven")
                .log();
        stringFlux.subscribe(System.out::println);
    }
}