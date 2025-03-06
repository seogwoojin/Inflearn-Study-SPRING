package hello.order.v4;

import hello.order.OrderService;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Timed("my.order")
public class OrderServiceV4 implements OrderService {

    private final MeterRegistry registry;

    private AtomicInteger stock = new AtomicInteger(100);

    public OrderServiceV4(MeterRegistry registry) {
        this.registry = registry;
    }

    @Override
    @Counted("my.order")
    public void order() {
        log.info("주문");
                    stock.decrementAndGet();
                    sleep(500);

    }

    private void sleep(int i){
        try {
            Thread.sleep(i+new Random().nextInt(200));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Counted("my.order")
    public void cancel() {
            log.info("취소");
            stock.incrementAndGet();
            sleep(500);

    }

    @Counted("my.order")
    @Override
    public AtomicInteger getStock() {
        return stock;
    }
}
