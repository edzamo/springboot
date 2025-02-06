package flux;

import reactor.core.publisher.Flux;

public class App {
    public static void main(String[] args) {
        Flux<String> stringFlux = Flux.just("Hi", "Mr. Pepito", "Hi", "Mr. Jose");
        //  stringFlux.subscribe(i-> System.out.println(i));
        stringFlux.subscribe(System.out::println);

        stringFlux.subscribe(r-> System.out.print(r.repeat(2)));
    }
}
