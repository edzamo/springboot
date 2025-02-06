package TwoMonos;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoOperator;

import java.time.Duration;

public class App {
    public static void main(String[] args) {

        Mono<String> stringMono = Mono.just("Hi Pepito").delayElement(Duration.ofSeconds(2));
        stringMono.subscribe(System.out::println);

        Mono<String> stringMono1 = Mono.just("Hi Mr. Jose").delayElement(Duration.ofSeconds(3));
        stringMono1.subscribe(System.out::println);

        Flux<String> mixFlux = Flux.concat(stringMono, stringMono1);
        mixFlux.subscribe(System.out::println);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

    }
}
