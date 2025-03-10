package hello.order.v4;

import hello.order.OrderService;
import io.micrometer.core.aop.CountedAspect;
import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfigV4 {

    @Bean
    OrderService orderService(MeterRegistry registry){
        return new OrderServiceV4(registry);
    }

    @Bean
    public CountedAspect countedAspect(MeterRegistry registry){
        return new CountedAspect(registry);
    }

    @Bean
    public TimedAspect timedAspect(MeterRegistry registry){
        return new TimedAspect(registry);
    }
}
