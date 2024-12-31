package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// 퍼블릭 지워도 됨
class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        /*
        실행 코드

        //soutv _ Tab
        System.out.println("memberService = " + memberService);
        System.out.println("memberService.getClass() = " + memberService.getClass());

        //출력값
        memberService = hello.core.member.MemberServiceImpl@1f53a5dc
        memberService.getClass() = class hello.core.member.MemberServiceImpl
         */
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름 없이 타입만으로 조회") void findBeanByType() {
        //타입으로만 조회하면 이름으로 조회하는 것보다 편리할 수는 있으나 타입이 여러 개일 때 좀 곤란
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }
    /*
    MemberService memberService = ac.getBean(MemberService.class);
    -> 인터페이스로 조회하는 것
    -> 인터페이스의 구현체가 대상이 됨
     */
    @Test
    @DisplayName("구체 타입으로 조회") void findBeanByName2() {
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);

        //좋은 방법 x -> 역할과 구현을 구분해야 하고 역할에 의존을 해야 하는데 위 코드는 구현에 의존하는 것
        //유연성이 좀 떨어지게됨
    }
    @Test
    @DisplayName("빈 이름으로 조회X") void findBeanByNameX() {
        //ac.getBean("xxxxx", MemberService.class);
        // 무조건 예외가 터져야 테스트 성공
        // 예외가 터지지 않으면 실패
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxxx", MemberService.class));
    }
}
