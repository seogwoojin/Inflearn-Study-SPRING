package hello.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
public class CheckedTest {
    @Test
    void checkedCatch(){
        Service service = new Service();
        service.callCatch();
    }

    @Test
    void checkedThrow() {
        Service service = new Service();

        assertThatThrownBy(service::callThrow).isInstanceOf(MyCheckedException.class);
    }
    /**
     * Exception 상속 예외는 체크 예외이다
     */
    static class MyCheckedException extends Exception{
        public MyCheckedException(String message){
            super(message);
        }
    }

    static class Service{
        Repository repository = new Repository();

        /**
         * 예외를 잡기
         */

        public void callCatch(){
            try {
                repository.call();
            } catch (MyCheckedException e) {
                e.printStackTrace();
                log.info("예외 처리 message = {}", e.getMessage(), e);
            }
        }

        public void callThrow() throws MyCheckedException {
            repository.call();
        }
    }
    static class Repository{
        public void call() throws MyCheckedException {
            throw new MyCheckedException("EX");
        }
    }

}
