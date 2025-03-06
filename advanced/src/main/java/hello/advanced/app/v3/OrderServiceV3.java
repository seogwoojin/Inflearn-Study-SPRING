package hello.advanced.app.v3;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {
    private final LogTrace logTrace;
    private final OrderRepositoryV3 orderRepository;

    public String orderItem(String itemId){
        TraceStatus status = null;
        try {
            status = logTrace.begin("OrderService.orderItem()");
            orderRepository.save(itemId);
            logTrace.end(status);
            return "OK";
        } catch (Exception e){
            logTrace.exception(status, e);
            throw e;
        }
    }
}
