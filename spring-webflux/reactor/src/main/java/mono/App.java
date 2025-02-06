package mono;

import dto.Message;
import reactor.core.publisher.Mono;

public class App {
    public static void main(String[] args) {
        // This is a first example with reactor
        // we Have a example of kind Mono
        // which is a un kind of observable
        Mono<String > myMono= Mono.just("Caracola");
        myMono.subscribe(System.out::println);

        Mono<Message> messageMono= Mono.justOrEmpty(new Message());
        messageMono.subscribe(m-> System.out.println(m));

        Mono<Message> messageMonoNull= Mono.justOrEmpty(null);
        messageMonoNull.subscribe(n-> System.out.println(n));



    }
}
