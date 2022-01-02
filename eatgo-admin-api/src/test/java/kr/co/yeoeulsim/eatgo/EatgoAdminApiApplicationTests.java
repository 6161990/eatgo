package kr.co.yeoeulsim.eatgo;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class EatgoAdminApiApplicationTests {

    @Test
    public void contextLoads() {
    }

}

/**
 * application, domain, interfaces 각각의 역할에 따라 test 해야 할 내용이 다르다.
 * 각 클래스에 있는 메소드 뿐만 아니라 메소드와 메소드 사이의 유기적인 기능과 역할을 검증할 수 있어야한다.
 *
 * 불편함 -> 도메인
 * 올바른 문제 의식 -> 무엇이 문제인가 -> 왜? -> 사용자 스토리 (사용자 입장에서 기능을 서술)
 * => (사용자)는 (가치)를 위해 (기능)을 할 수 있다.
 * => 사용자는 고객, 가게, 관리자 일 수 있다.
 *
 * 어떻게 만들 것인가?
 * 1. 도메인 모델링
 * 해결하려는 문제에서 쓰이는 각 개념들을 정의하고 필요한 것들을 알아보는 것.
 * EX) Restaurant, Menu Item (Food & Beverage), User, Favorite, Review, Reservation
 * 2. 시스템 아키텍처
 * = 소프트웨어 + 하드웨워 + 인프라
 * 제약 조건을 걸어야한다. Web, Mobile 둘 다 할건데 각각 다른 부분(프론트)과 공통(백엔드)인 부분이있다.
 *
 * Multi-tier Architecture :
 * 여러 개의 계층으로 나눠서 애플리케이션을 구성 (3 tier Architecture)
 * 1. Presentation : 사용자와 소통하는 부분 (FrontEnd - HTML CSS JS)
 * 2. Business : 사용자의 요청을 처리하는 부분 (BackEnd - REST API)
 *      Business 는 또 다시 Layered Architecture 로 나뉜다.
 *      => 각 레이어는 바로 아래 또는 그보다 아래있는 것들에게 의존하고,
 *         밑에 있는 레이어는 위에있는 레이어를 쓸 수 없다. (프로그램의 복잡도를 낮추가 위함)
 *      1. UI Layer
 *      2. Application Layer
 *      3. Domain Layer
 *      4. Infrastructure Layer
 * 3. Data Source : 처리한 것들이 저장되는 곳 (Database - DBMS)
 *
 *
 * */
