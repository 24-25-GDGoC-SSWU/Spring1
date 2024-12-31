package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class StatefulServiceTest {
    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        //빈 조회 (두 개 꺼내옴)
        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

        //ThreadA: A사용자 10000원 주문
        //statefulService1.order("userA", 10000);
        int uesrAPrice = statefulService1.order("userA", 10000); // 지역 변수기 때문에 사용자 a,b값이 다름
        //ThreadB: B사용자 20000원 주문
        //statefulService2.order("userB", 20000); //A가 주문을 하고 조회하는 사이에 B가 끼어듦
        int uesrBPrice = statefulService1.order("userB", 20000);

        //ThreadA: 사용자A 주문 금액 조회
        //int price = statefulService1.getPrice();
        //ThreadA: 사용자A는 10000원을 기대했지만, 기대와 다르게 20000원 출력
        //System.out.println("price = " + price);
        System.out.println("price = " + uesrAPrice);

        // 두 개의 값이 같냐
        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}