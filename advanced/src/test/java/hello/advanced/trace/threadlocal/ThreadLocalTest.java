package hello.advanced.trace.threadlocal;

import hello.advanced.trace.threadlocal.code.FieldService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ThreadLocalTest {
    private FieldService fieldService= new FieldService();

    @Test
    void field(){
        log.info("main start");
        Runnable userA = () -> fieldService.logic("userA");
        Runnable userB = () -> fieldService.logic("userB");
        Thread threadA = new Thread(userA);
        Thread threadB = new Thread(userB);

        threadA.setName("thread-A");
        threadB.setName("thread-B");
        threadA.start();
        sleep(100);
        threadB.start();
        sleep(2000);

    }

    private void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
