package hello.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
public class UnCheckedTest {
    static class MyUncheckedException extends RuntimeException {
        public MyUncheckedException(String message){
            super(message);
        }
    }

    @Test
    void unCheck(){
        Service service = new Service();
        assertThatThrownBy(service::callThrow).isInstanceOf(MyUncheckedException.class);
    }
    static class Repository{
        public void call(){
            throw new MyUncheckedException("ex");
        }
    }

    static class Service{
        Repository repository = new Repository();
        public void callThrow() throws MyUncheckedException {
            repository.call();
        }
    }
}
