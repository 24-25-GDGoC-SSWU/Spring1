package hello.hello.repository;

import hello.hello.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
//static import -> Assertions.assertThat(result).isEqualTo(member); -> 이런 식으로 작성하지 않아도 됨

class MemoryMemberRepositoryTest { // 다른 데서 가져다 쓸 것이 아니기 때문에 public으로 작성할 필요 없음
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 메소드가 실행이 끝날 때마다 동작하는 것
    public void afterEach() { // 테스트가 끝날 때마다 리포지토리를 깔끔하게 지워주는 코드
        repository.clearStore();
    }

    @Test
    public void save() { //save 기능이 제대로 작동하는지 테스트 가능
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        repository.save(member);

        //then
        Member result = repository.findById(member.getId()).get(); // 반환타입이 optional임
                                                                   // -> optional에서 값을 꺼낼때는 .get() 사용 but 좋은 방법은 아님 test 코드 같은 데에선 사용
        assertThat(result).isEqualTo(member); // assultThat에서 member가 result랑 똑같다
    }

    @Test
    public void findByName() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1); //member1 저장 -> 회원 가입 완료

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2); //member2 저장 -> 회원 가입 된 것

        //when
        Member result = repository.findByName("spring1").get(); //spring1을 꺼내옴

        //then
        assertThat(result).isEqualTo(member1); //member1아 result와 같다
    }

    @Test
    public void findAll() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //when
        List<Member> result = repository.findAll(); // 리스트에 몇 개가 들어있는 지 확인

        //then
        assertThat(result.size()).isEqualTo(2); //
    }
}
