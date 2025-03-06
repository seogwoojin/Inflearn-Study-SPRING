package hello.advanced.trace.template;

import hello.advanced.trace.template.code.AbstractTemplate;
import hello.advanced.trace.template.code.SubClassLogic1;
import hello.advanced.trace.template.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateMethodTest {

    AbstractTemplate abstractTemplate = new AbstractTemplate() {
        @Override
        protected void call() {
            log.info("비지 1");
        }
    };
    AbstractTemplate abstractTemplate2 = new SubClassLogic2();
    @Test
    void templateMethodV0(){
        abstractTemplate.execute();
        System.out.println("abstractTemplate.getClass() = " + abstractTemplate.getClass());
        abstractTemplate2.execute();
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
