package pe.edu.cibertec.appgrupo6consumidor.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    public static final String EXCHANGE = "Grupo6Exchange";
    public static final String QUEUE = "Grupo6Queue";
    public static final String ROUTING_KEY = "Grupo6Routing";

    @Bean
    public DirectExchange fibonacciExchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    public Queue fibonacciQueue() {
        return new Queue(QUEUE, true);
    }

    @Bean
    public Binding fibonacciBinding() {
        return BindingBuilder.bind(fibonacciQueue())
                .to(fibonacciExchange()).with(ROUTING_KEY);
    }

}