package hello.advanced.trace.strategy.code.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * 필드에 전략 보관
 */
@Slf4j
public class ContextV1 {
    private Strategy strategy;

    public ContextV1(Strategy strategy){
        this.strategy = strategy;
    }

    public void execute(){
        long startTime = System.currentTimeMillis();
        //비지니스 로직
        strategy.call();
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime = {}", resultTime);
    }

}
