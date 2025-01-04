package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
    /*@Rollback(false)*/  // 롤백이지만, 그래도 일단 DB에 쿼리 날리는 걸 보고 싶다면
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long savedId = memberService.join(member);

        //then
        /*//flush는 영속성 컨텍스트에 있는 어떤 변경이나 등록 내용을 그 데이터베이스에 반영하는 거임
        em.flush();*/
        assertEquals(member, memberRepository.findOnde(savedId));

    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1. setName("kim");

        Member member2 = new Member();
        member2. setName("kim");

        //when
        memberService.join(member1);
      /* try{  */
            memberService.join(member2); // 예외가 발생해야 한다!!!
    /*    } catch (IllegalStateException e) {
            return; //얘는 정상적으로 리턴을 하기 때문에 테스트 케이스가 성공함
        }*/

        //then
        // fail() - 여기 오면 안 되는 거임
        // 여기오면 “예외가 발생해야 한다.”고 fail을 떨군다
        fail(" 예외가 발생해야 한다.");
    }

}