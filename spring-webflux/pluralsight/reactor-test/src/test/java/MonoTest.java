import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

public class MonoTest {

    @Test
    public void firstMono() {
        Mono.just("A");

    }

    @Test
    public void monoWithConsumer() {
        Mono.just("A")
                .log()
                .subscribe(System.out::println);
    }

    @Test
    public void monoWithDoOn() {
        Mono.just("A")
                .log()
                .doOnSubscribe(subscription -> System.out.println("subscription: " + subscription))
                .doOnRequest(request -> System.out.println("request: " + request))
                .doOnSubscribe(complete -> System.out.println("complete: " + complete))
                .subscribe(System.out::println);
    }

    @Test
    public void monoEmpty() {
        Mono.empty()
                .log()
                .subscribe(System.out::println);
    }

    @Test
    public void emptyCompleteConsumerMono() {
        Mono.empty()
                .log()
                .subscribe(
                        System.out::println,
                        null,
                        () -> System.out.println("Done")
                );
    }

    @Test
    public void errorRuntimeExceptionMono() {
        Mono.error(
                        new RuntimeException()
                ).log()
                .subscribe();
    }

    /**
     * Tradicional try and catch
     */
    @Test
    public void errorExceptionMono() {
        Mono.error(
                        new Exception()
                ).log()
                .subscribe();
    }

    @Test
    public void errorConsumerMono() {
        Mono.error(
                        new Exception()
                ).log()
                .subscribe(
                        System.out::println,
                        e -> System.out.println("Error : " + e)
                );
    }

    @Test
    public void errorOnErrorResumeMono() {
        Mono.error(
                        new Exception()
                ).onErrorResume(
                        e -> {
                            System.out.println("Caught : " + e);
                            return Mono.just("B");
                        }
                ).log()
                .subscribe();
    }
}
