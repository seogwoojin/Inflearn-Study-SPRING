package hello.jdbc.exception.basic;

import org.junit.jupiter.api.Test;

import java.net.ConnectException;
import java.sql.SQLException;

public class CheckedAppTest {
    @Test
    public void test() throws SQLException, ConnectException {
        Controller controller = new Controller();
        controller.controller();
    }
    static class Service{
        NetworkClient networkClient = new NetworkClient();
        Repository repository = new Repository();

        public void service() throws ConnectException, SQLException {
            networkClient.callThrow();
            repository.call();
        }

    }

    static class Controller{
        Service service = new Service();
        public void controller() throws SQLException, ConnectException {
            service.service();
        }
    }
    static class NetworkClient{
        public void callThrow() throws ConnectException {
            throw new ConnectException("연결실패");
        }
    }

    static class Repository{
        public void call() throws SQLException {
            throw new SQLException();
        }
    }
}
