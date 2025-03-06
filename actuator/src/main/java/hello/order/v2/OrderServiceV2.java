package hello.order.v2;

import hello.order.OrderService;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class OrderServiceV2 implements OrderService {

    private final MeterRegistry registry;

    private AtomicInteger stock = new AtomicInteger(100);

    public OrderServiceV2(MeterRegistry registry) {
        this.registry = registry;
    }

    @Override
    @Counted("my.order")
    public void order() {
        log.info("주문");
        stock.decrementAndGet();
    }

    @Override
    @Counted("my.order")
    public void cancel() {
        log.info("취소");
        stock.incrementAndGet();
    }

    @Counted("my.order")
    @Override
    public AtomicInteger getStock() {
        return stock;
    }
}
