import com.pluralsight.rxjava.dto.SpaceShip;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

public class FlapMapAndMapsTest {

    private Flux<SpaceShip> flux() {
        return Flux.just(
                        new SpaceShip("Halk", 10),
                        new SpaceShip("Eagle", 20),
                        new SpaceShip("Round", 30),
                        new SpaceShip("Pyramid", 50)
                )
                .delayElements(Duration.ofSeconds(1));
    }


    @Test
    void tryFlapMaps() throws InterruptedException {
        Flux<SpaceShip> spaceShipFlux = flux();
        flux()
                .map(s -> {
                    s.setId(-1);
                    return s;
                })
                .flatMap(s ->
                        Flux.just(s, new SpaceShip(-100, s.getName() + "large", s.getAmount() * 2))
                )
                .subscribe(System.out::println);

        Thread.sleep(10000);
    }



}
