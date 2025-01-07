package hello.jdbc.repository;

import hello.jdbc.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryV0Test {
    MemberRepositoryV0 memberRepositoryV0 = new MemberRepositoryV0();

    @Test
    public void save() throws SQLException {
        memberRepositoryV0.save(new Member("woojin", 10000));
    }

}