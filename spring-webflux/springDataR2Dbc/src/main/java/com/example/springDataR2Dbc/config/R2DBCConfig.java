package com.example.springDataR2Dbc.config;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.context.annotation.Bean;

import static io.r2dbc.pool.PoolingConnectionFactoryProvider.MAX_SIZE;
import static io.r2dbc.spi.ConnectionFactoryOptions.*;

public class R2DBCConfig {
    @Bean
    public ConnectionFactory connectionFactory() {
        return ConnectionFactories.get(
                ConnectionFactoryOptions.builder()
                        .option(DRIVER, "mysql")
                        .option(HOST, "localhost")
                        .option(PORT, 3306)
                        .option(USER, "root")
                        .option(PASSWORD, "admin123")
                        .option(DATABASE, "demo")
                        .option(MAX_SIZE, 40)
                        .build());
    }
}
