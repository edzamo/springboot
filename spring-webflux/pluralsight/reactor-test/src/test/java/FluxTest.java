import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

import java.lang.reflect.Array;
import java.time.Duration;
import java.util.Arrays;

public class FluxTest {

    @Test
    public void firstFlux() {
        Flux.just("A", "B", "C")
                .log()
                .subscribe();
    }

    @Test
    public void firstFluxIterable() {
        /*Flux.just(Arrays.asList("A","B","C"))
                .log()
                .subscribe(System.out::println);
        */
        Flux.fromIterable(Arrays.asList("A", "B", "C"))
                .log()
                .subscribe();
    }

    @Test
    public void fluxFromRange() {
        Flux.range(1, 10)
                .log()
                .subscribe();
    }

    @Test
    public void fluxFromInterval() throws InterruptedException {
        Flux.interval(Duration.ofSeconds(1))
                .log()
                .take(2)
                .subscribe();
        Thread.sleep(5000);
    }

    @Test
    public void fluxRequest() {
        Flux.range(1, 5)
                .log()
                /*  .subscribe(
                          null,
                          null,
                          null,
                          s-> s.request(3)*/
                .subscribe(
                        new BaseSubscriber<Integer>() {
                            @Override
                            protected void hookOnSubscribe(Subscription subscription) {
                                subscription.request(3L);
                            }
                        }

                );
    }

    @Test
    void fluxWithAnError() {
        Flux<Integer> integerFlux = Flux.range(1, 4)
                .map(i -> {
                    if (i <= 3) return i;
                    throw new RuntimeException("Got to 4");
                });
        integerFlux.subscribe(
                i -> System.out.println(i),
                error -> System.err.println("Error" + error));
    }

    @Test
    void fluxWithBothError(){
        Flux<Integer> integerFlux = Flux.range(1, 4);
                integerFlux.subscribe(
                        i-> System.out.println(i),
                        error-> System.err.println("Error : " + error),
                        ()-> System.out.println("Done")
                );
    }

}
