# Inflearn의 Spring JPA 강의를 들으며 직접 작성한 코드들을 모은 공간입니다.

Readme를 통해 각각의 강의를 배우며 알게 된 지식을 저장하고 있습니다.

## <a href="https://github.com/seogwoojin/Inflearn-Study-SPRING-JPA/tree/main/jdbc">JDBC</a>

### JDBC 태동
---

- JDBC는 DB와의 연결을 위한 **인터페이스** ( 이전에는 MySql, Oracle 등의 DB에 연결하고, SQL문을 요청하는 로직을 매번 새롭게 만들었어야 함 ) 

추상화를 통해서 이 문제를 해결 -> 유저는 JDBC Interface를 통해서만 요청 (java.sql.Connection 인터페이스에 의존)

이를 구현하는 각각의 구현체들은 DB 별로 구현체 라이브러리를 가지고 있고 이를 의존성에 추가해서 사용한다. (h2, postgres, mysql ...)

이때 어떤 구현체를 사용할 지 정하는 것은 java.sql.DriverManager 이다. 참고로 java.sql은 자바 진영의 DB 관련 표준 기술이다.

<br/>

하지만 이런 Connection을 매 SQL 요청마다 생성하는 것은 비효율적 (매번 TCP/IP connection)

### DataSource (커넥션 풀 인터페이스)
--- 

- 이에 대한 해결책으로 DB Connection을 미리 만들어 놓고 보관하기로 결정. 현재는 구현체로 HikariCP가 성능 & 안전성 우월

스프링 실행 과정에서 병렬적으로 Connection 객체가 생성되며 Default는 10임.

이를 통해 얻을 수 있는 장점은 **DB 접근 속도 증가 + 구현과 사용의 분리**이다.

### 트랜잭션 
---

상황에 따라 여러 SQL이 한 로직 내에서 실행되야 하는 경우. 트랜잭션이 필요하다. 

이 트랜잭션을 스프링 위에서 직접 구현하기 위해서 꽤나 많은 품이 들어간다 (구현 코드 증가, 커넥션 관리)

- 이를 단 한줄로 구현 한 것이 @Transactional 어노테이션이다.

스프링 AOP를 통해 프록시 객체에서 autoCommit(false) Connection을 유지하고 최종적으로 commit() 혹은 rollback() 등을 수행한다. 


## <a>토비 스프링 부트</a>

위 강의는 @SpringBootApplication 어노테이션이 담고 있는 기능을 직접 구현해보며, 스프링 부트가 가진 추상화의 장점을 알려준다.

강의를 통해 이해할 수 있는 스프링 부트의 장점은 다음과 같다. 

1. 서블릿 컨테이너의 내장화 (초기 환경설정 시간을 매우 단축)
2. 버전 관리 Gradle 파일을 통해 스프링 부트 버전에 적절한 각 라이브러리들의 버전을 자동으로 선택해줌
3. 상태 모니터링 (위에 2개가 중요하다고 생각)

DB와 통신할 때도 순수 자바 코드를 이용하듯, Http에 대해 스프링 컨테이너는 모르도록 설계했다고 이해했다.

순수 http header, uri, json은 서블릿 컨테이너 단에서 해결하고, 컨트롤러로 매핑한 이후부터는 순수 자바로 이루어진 부분이다.