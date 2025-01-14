package hello.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.net.ConnectException;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
@Slf4j
public class UnCheckedAppTest {
    @Test
    public void test()  {
        Controller controller = new Controller();
        assertThatThrownBy(controller::controller).isInstanceOf(RuntimeException.class);
    }
    static class Service{
        NetworkClient networkClient = new NetworkClient();
        Repository repository = new Repository();

        public void service()   {
            networkClient.callThrow();
            repository.call();
        }

    }

    static class Controller{
        Service service = new Service();
        public void controller()  {
            service.service();
        }
    }
    static class NetworkClient{

        public void callThrow()  {
            try {
                throw new ConnectException("연결실패");
            }catch (Exception e){
                throw new RuntimeConnectionException(e);
            }

        }
    }

    static class RuntimeConnectionException extends RuntimeException {
        public RuntimeConnectionException(Throwable cause){
            super(cause);
        }
    }

    static class RuntimeSQLException extends RuntimeException {
        public RuntimeSQLException(Throwable cause){
            super(cause);
        }
    }
    static class Repository{
        public void call() {
            try {
                throw new SQLException();
            } catch (Exception e){
                throw new RuntimeSQLException(e.getCause());
            }
        }
    }

    @Test
    void printEx(){
        Controller controller = new Controller();

        try {
            controller.controller();
        } catch (Exception e){
            log.info("ex",e);
        }
    }
}
