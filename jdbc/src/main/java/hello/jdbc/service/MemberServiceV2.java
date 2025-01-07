package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV1;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


@RequiredArgsConstructor
@Slf4j
public class MemberServiceV2 {
    private final DataSource dataSource;
    private final MemberRepositoryV1 memberRepositoryV1;


    public void accountTransfer(String fromId, String toId, int money) throws SQLException {
        Connection con = dataSource.getConnection();
        try {
            con.setAutoCommit(false);
            bizLogic(con, fromId, toId, money);
            con.commit();
        } catch (Exception e){
            con.rollback();
            throw new IllegalStateException(e);
        } finally {
            if (con != null){
                try {
                    con.setAutoCommit(true);
                    con.close();
                }catch (Exception e){
                    log.info("error", e);
                }
            }
        }

    }

    private void bizLogic(Connection con, String fromId, String toId, int money) throws SQLException {
        Member fromMember = memberRepositoryV1.findById(con,fromId);
        Member toMember = memberRepositoryV1.findById(con,toId);

        memberRepositoryV1.update(con,fromId, fromMember.getMoney()- money);

        validation(toMember);
        memberRepositoryV1.update(con,toId, toMember.getMoney()+money);

    }


    private void validation(Member toMember){
        if(toMember.getMemberId().equals("ex")){
            throw new IllegalStateException("이체 중 예외 발생");
        }
    }
}
