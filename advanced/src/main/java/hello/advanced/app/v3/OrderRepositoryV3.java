package hello.advanced.app.v3;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV3 {
    private final LogTrace logTrace;
    public void save(String itemId){
        TraceStatus status = null;
        try {
            status = logTrace.begin("OrderRepository.save()");
            sleep(10000);
            logTrace.end(status);
        } catch (Exception e){
            logTrace.exception(status, e);
            throw e;
        }
    }
    private void sleep(int mills){
        try{
            Thread.sleep(mills);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
