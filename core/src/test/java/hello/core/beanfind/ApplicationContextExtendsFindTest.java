package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FIxDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

public class ApplicationContextExtendsFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 중복 오류가 발생한다")
    void findBeanByParentTypeDuplicate() {
        //DiscountPolicy bean = ac.getBean(DiscountPolicy.class); // 부모 타입을 조회했는데 자식 두 개가 다 걸려서 에러 발생
        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 빈 이름을 지정하면 된다") void findBeanByParentTypeBeanName() {
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        //타입은 DiscountPolicy이지만 실제 구현 개체는 rateDiscountPolicy얘가 나올 것
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("특정 하위 타입으로 조회") void findBeanBySubType() {
        //별로 좋지 않은 방법
        RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기") void findAllBeanByParentType() {
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        assertThat(beansOfType.size()).isEqualTo(2);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value=" + beansOfType.get(key));
            //실제 테스트 케이스 할 때는 출력물을 만들지 않는 게 좋음(디버그 하고 싶을 때는 남겨도 괜찮긴 함)
            //-> 통과 실패는 시스템이 결정하게 해야지 눈으로 보고 있을 순 없음
        }
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기 - Object") void findAllBeanByObjectType() {
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value=" +
                    beansOfType.get(key));
        }
    }

    @Configuration
    static class TestConfig {
        // 해당 코드에서만 사용
        // 테스트 용
        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        /*

        public RateDiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }
        -> 이렇게 지정해도 똑같음
        그러나 다르게 지정하는 이유는 개발하거나 설계를 할 때 역할과 구현을 항상 쪼개자고 했음
        -> public DiscountPolicy rateDiscountPolicy() -> 얘를 보고 역할을 보는 것
        구체적인 것으로(RateDiscountPolicy)로 해도 되기는 하나 DiscountPolicy이렇게 해두는 게 훨씬 좋음
        -> 다른 애에서 의존 관계 주입을 할 때도 그 애를 의존하는 것이기 때문에 그 애만 보면 돼서
         */

        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FIxDiscountPolicy();
        }
    }
}
