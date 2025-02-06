package com.pluralsight.productapiannotation;

import com.pluralsight.productapiannotation.model.Product;
import com.pluralsight.productapiannotation.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class ProductApiAnnotationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductApiAnnotationApplication.class, args);
    }

    /**
     * CommandLineRunner
     * <p>
     * This element is a functional Interfaces, this interfaces help with an initializer of something
     * for example charge the info in the data base
     * Inject in the Bdd
     */
    @Bean
    CommandLineRunner innit(ReactiveMongoOperations operations, ProductRepository repository) {
        return args -> {

            Flux<Product> productFlux = Flux.just(
                            new Product(null, "Big Latte", 2.99),
                            new Product(null, "Big Decaf", 2.99),
                            new Product(null, "Green Tea", 1.99)
                    )
                    .flatMap(repository::save);
            //Exist two form for to save in the bdd
            //.flatMap(product -> repository.save(product));


            /**
             * thenMany then
             * After of complete of operation and then  execute de publisher
             * the difference in both operation is
             * Can Receive any parameter or Mono
             * Can receive one o more parameter( Flux o Mono)
             */
            productFlux
                    .thenMany(repository.findAll())
                    .subscribe(System.out::println);

            /* reactive MongoDB
            operations.collectionExists(Product.class)
                    .flatMap(exists -> exists ? operations.dropCollection(Product.class) : Mono.just(exists))
                    .thenMany(v -> operations.createCollection(Product.class))
                    .thenMany(productFlux)
                    .thenMany(repository.findAll())
                    .subscribe(System.out::println);
             */

        };


    }
}
