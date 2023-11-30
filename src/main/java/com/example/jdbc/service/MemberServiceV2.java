package com.example.jdbc.service;

import com.example.jdbc.domain.Member;
import com.example.jdbc.repository.MemberRepositoryV1;
import com.example.jdbc.repository.MemberRepositoryV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 트랜잭션 - 파라미터로 커넥션을 준다.
 * */
@Slf4j
@RequiredArgsConstructor
public class MemberServiceV2 {

    private final DataSource dataSource;
    private final MemberRepositoryV2 memberRepository;

    public void accountTransfer(String fromId, String toId, int money) throws SQLException {
        Connection connection = dataSource.getConnection();
        try {
            connection.setAutoCommit(false); // 오토커밋 false = 트랜잭션 시작

            doTransfer(fromId, toId, money, connection);

            connection.commit(); // 성공시 커밋
        } catch (Exception e) {
            connection.rollback(); // 실패시 롤백
            throw new IllegalStateException(e);
        } finally {
            release(connection);
        }
    }

    private void doTransfer(String fromId, String toId, int money, Connection connection) {
        Member fromMember = memberRepository.findById(connection, fromId);
        Member toMember = memberRepository.findById(connection, toId);

        memberRepository.update(connection, fromId, fromMember.getMoney() - money);

        validation(toMember);

        memberRepository.update(connection, toId, toMember.getMoney() + money);
    }

    private void release(Connection connection) {
        if (connection != null) {
            try {
                connection.setAutoCommit(true); // DataSource의 구현체를 HikariCP로 사용할 땐, 커넥션 풀로 돌아가기 때문에 다시 기본값을 가진 상태로 돌아가게 해야하므로.
                connection.close();
            } catch (Exception e) {
                log.error("error: ", e);
            }
        }
    }

    private void validation(Member toMember) {
        if (toMember.getMemberId().equals("ex")) {
            throw new IllegalStateException("Error occurred");
        }
    }
}
