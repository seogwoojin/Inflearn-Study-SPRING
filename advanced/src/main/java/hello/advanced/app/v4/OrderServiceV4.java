package hello.advanced.app.v4;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV4 {
    private final LogTrace logTrace;
    private final OrderRepositoryV4 orderRepository;

    public String orderItem(String itemId){
        AbstractTemplate<Void> abstractTemplate = new AbstractTemplate<>(logTrace) {
            @Override
            protected Void call() {
                super.call2();
                orderRepository.save(itemId);
                return null;
            }
        };
        return abstractTemplate.execute("OrderService.orderItem()").toString();
    }
}
