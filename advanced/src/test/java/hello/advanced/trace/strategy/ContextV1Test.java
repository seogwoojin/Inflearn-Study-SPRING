package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.strategy.ContextV1;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic1;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic2;
import hello.advanced.trace.template.code.AbstractTemplate;
import hello.advanced.trace.template.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {

    ContextV1 contextV1 = new ContextV1(()->log.info("비지니스1"));
    ContextV1 contextV2 = new ContextV1(()->log.info("비지니스2"));
    @Test
    void templateMethodV0(){
        contextV1.execute();
        contextV2.execute();
    }

    private void logic1(){
        long startTime = System.currentTimeMillis();
        //비지니스 로직
        log.info("비지니스 로직 1 실행");

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime = {}", resultTime);
    }

    private void logic2(){
        long startTime = System.currentTimeMillis();
        //비지니스 로직
        log.info("비지니스 로직 2 실행");

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime = {}", resultTime);
    }
}
