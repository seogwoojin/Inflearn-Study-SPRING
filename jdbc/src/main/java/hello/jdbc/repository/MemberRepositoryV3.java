package hello.jdbc.repository;

import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.NoSuchElementException;

@Slf4j
public class MemberRepositoryV3 {

    private final DataSource dataSource;

    public MemberRepositoryV3(DataSource dataSource){

        this.dataSource = dataSource;
    }

    private void close(Connection conn, Statement stmt, ResultSet rs){
//        JdbcUtils.closeConnection(conn);
        JdbcUtils.closeStatement(stmt);
        JdbcUtils.closeResultSet(rs);
        // 트랜잭션 동기화를 사용하려면 DataSourceUtils를 사용
        DataSourceUtils.releaseConnection(conn, dataSource);
    }
    private Connection getConn() throws SQLException {
        Connection con = DataSourceUtils.getConnection(dataSource);
        log.info("get Connection = {}, class={}", con, con.getClass());
        return con;
    }

    public Member save(Member member) throws SQLException {
        String sql = "insert into member(member_id, money) values (?,?)";
        Connection conn = null;
        PreparedStatement pstmt = null;


        try {
            conn = getConn();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, member.getMemberId());
            pstmt.setInt(2, member.getMoney());
            int count = pstmt.executeUpdate();
            return member;
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        } finally {
            close(conn, pstmt, null);
        }
    }

    public Member findById(String memberId) throws SQLException {
        String sql = "select * from member where member_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConn();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberId);

            rs = pstmt.executeQuery();
            if(rs.next()){
                Member member = new Member();
                member.setMemberId(rs.getString("member_id"));
                member.setMoney(rs.getInt("money"));
                return member;
            } else{
                throw new NoSuchElementException("member not found memberId ="+ memberId);
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            close(conn, pstmt, rs);
        }
    }
    public void update(String memberId, int money) throws SQLException {
        String sql = "update member set money=? where member_id=?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConn();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, money);
            pstmt.setString(2, memberId);

            int i = pstmt.executeUpdate();
            log.info("resultSize = {}", i);

        } catch (SQLException e) {
            throw e;
        } finally {
            close(conn, pstmt, rs);
        }
    }

    public void delete(String memberId) throws SQLException {
        String sql = "delete from member where member_id=?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConn();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberId);

            int i = pstmt.executeUpdate();
            log.info("resultSize = {}", i);

        } catch (SQLException e) {
            throw e;
        } finally {
            close(conn, pstmt, null);
        }
    }
}
