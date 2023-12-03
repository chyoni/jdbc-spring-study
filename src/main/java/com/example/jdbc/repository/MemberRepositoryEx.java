package com.example.jdbc.repository;

import com.example.jdbc.domain.Member;

import java.sql.SQLException;

/**
 * 좋지 못한 인터페이스. 왜냐하면 인터페이스를 사용하는 목적은 추상화인데 (추상화란, 구현체가 어떤것이 됐던 그 구현 기술에 종속적이지 않고 인터페이스만을 의존하여 구현체의 기술을 사용하는 것)
 * 인터페이스가 SQLException 이라는 의존 관계를 가지고 있으면 이 인터페이스를 의존하는 서비스는 애시당초에 구현 기술에 종속적이게 되는 것.
 * 그러니까 이 인터페이스를 구현할 수 있는 구현체는 JDBC 기술밖에 안되는 것이니까. (JDBC 기술이 SQLException을 가지고 있음)
 * 그래서 인터페이스에는 throws 선언을 하면 안된다. 인터페이스는 순수한 인터페이스가 되어야 한다.
 * */
public interface MemberRepositoryEx {
    Member save(Member member) throws SQLException;

    Member findById(String memberId) throws SQLException;

    void update(String memberId, int money) throws SQLException;

    void delete(String memberId) throws SQLException;
}
