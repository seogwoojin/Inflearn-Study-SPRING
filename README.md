# Inflearn의 Spring JPA 강의를 들으며 직접 작성한 코드들을 모은 공간입니다.

Readme를 통해 각각의 강의를 배우며 알게 된 지식을 저장하고 있습니다.

## <a href="https://github.com/seogwoojin/Inflearn-Study-SPRING-JPA/tree/main/jdbc">JDBC</a>

### JDBC 태동

- JDBC는 DB와의 연결을 위한 **인터페이스** ( 이전에는 MySql, Oracle 등의 DB에 연결하고, SQL문을 요청하는 로직을 매번 새롭게 만들었어야 함 ) 

추상화를 통해서 이 문제를 해결 -> 유저는 JDBC Interface를 통해서만 요청 (java.sql.Connection 인터페이스에 의존)

이를 구현하는 각각의 구현체들은 DB 별로 구현체 라이브러리를 가지고 있고 이를 의존성에 추가해서 사용한다. (h2, postgres, mysql ...)

이때 어떤 구현체를 사용할 지 정하는 것은 java.sql.DriverManager 이다. 참고로 java.sql은 자바 진영의 DB 관련 표준 기술이다.

<br/>

하지만 이런 Connection을 매 SQL 요청마다 생성하는 것은 비효율적 (매번 TCP/IP connection)

### DataSource (커넥션 풀 인터페이스)

- 이에 대한 해결책으로 DB Connection을 미리 만들어 놓고 보관하기로 결정. 현재는 구현체로 HikariCP가 성능 & 안전성 우월

스프링 실행 과정에서 병렬적으로 Connection 객체가 생성되며 Default는 10임.

이를 통해 얻을 수 있는 장점은 **DB 접근 속도 증가 + 구현과 사용의 분리**이다.
