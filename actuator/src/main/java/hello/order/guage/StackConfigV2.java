package hello.order.guage;

import hello.order.OrderService;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.Guard;

@Configuration
@Slf4j
public class StackConfigV2 {

    @Bean
    public MeterBinder myStockMetric(OrderService orderService){
        return registry -> {
            Gauge.builder("my.stock", orderService, service -> {
                log.info("stock gauge");

                return service.getStock().get();
            }).register(registry);
        };
    }

}
