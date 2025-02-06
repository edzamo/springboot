import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class OperatorTest {


    @Test
    public void map() {
        Flux.range(1, 5)
                .map(i -> i * 10)
                .log()
                .subscribe(System.out::println);
    }

    @Test
    public void flap() {
        Flux.range(1, 5)
                .flatMap(i -> Flux.range(i * 10, 5))
                .log()
                .subscribe(System.out::println);
    }


    @Test
    public void flapMapMany() {
        Mono.just(3)
                .flatMapMany(i -> Flux.range(i * 10, 5))
                // .log()
                .subscribe(System.out::println);
    }

    @Test
    public void concat() throws InterruptedException {
        Flux<Integer> oneToFive = Flux.range(1, 5)
                .delayElements(Duration.ofMillis(200));
        Flux<Integer> sixToTen = Flux.range(6, 5)
                .delayElements(Duration.ofMillis(400));

        Flux.concat(oneToFive, sixToTen)
                .subscribe(System.out::println);
        Thread.sleep(4000);
    }

    @Test
    public void merge() throws InterruptedException {
        Flux<Integer> oneToFive = Flux.range(1, 5)
                .delayElements(Duration.ofMillis(200));
        Flux<Integer> sixToTen = Flux.range(6, 5)
                .delayElements(Duration.ofMillis(400));

        oneToFive.mergeWith(sixToTen)
                //  .log()
                .subscribe(System.out::println);
        Thread.sleep(4000);
    }

    @Test
    void zip() {
        Flux<Integer> oneToFive = Flux.range(1, 5);
        Flux<Integer> sixToFive = Flux.range(6, 5);
        Flux.zip(oneToFive, sixToFive, (x, y) -> x + ", " + y)
                .subscribe(System.out::println);

    }
    @Test
    void zipWith() {
        Flux<Integer> oneToFive = Flux.range(1, 5);
        Flux<Integer> sixToFive = Flux.range(6, 5);

        oneToFive.zipWith(sixToFive).subscribe(System.out::println);
    }

}
