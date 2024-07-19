package com.richardvinz.springboot_webflux_demo.firstLesson;

import reactor.core.publisher.Mono;

public class MonoFluxTest {


    public void testMono(){
        Mono<String> monoString = Mono.just("Vincent");
    }
}
