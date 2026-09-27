package pe.edu.cibertec.appgrupo6consumidor.rabbitmq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import pe.edu.cibertec.appgrupo6consumidor.config.RabbitMqConfig;
import pe.edu.cibertec.appgrupo6consumidor.service.FibonacciService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Slf4j
@Component
public class FibonacciConsumidor {

    private final FibonacciService fibonacciService;

    @RabbitListener(queues = RabbitMqConfig.QUEUE)
    public void recibirNumeros(String cadenaNumeros)
            throws InterruptedException {

        log.info("Cadena recibida: " + cadenaNumeros);

        Integer[] integerArray = Stream.of(cadenaNumeros.split(";"))
                .map(String::trim)
                .map(Integer::parseInt)
                .toArray(Integer[]::new);

        List<Integer> posiciones = Arrays.asList(integerArray);

        List<Long> resultado =
                fibonacciService.calculateSequence(posiciones);

        Thread.sleep(20000);

        log.info("Resultado Fibonacci: " + resultado);
    }
}
