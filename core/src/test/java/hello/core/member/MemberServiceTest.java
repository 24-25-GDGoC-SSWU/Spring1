package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MemberServiceTest {
    MemberService memberService;

    @BeforeEach //실행하기 전에 무조건 실행되는 거
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }
    @Test
    void join() {
        //내가 join한 거랑 찾은 거랑 똑같냐를 테스트

        //given
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then
        Assertions.assertThat(member).isEqualTo(findMember);
    }
    // 눈으로 콘솔로 보면서 출력된 결과물을 보며 검증을 하는 게 아님
    // 테스트 돌렸을 때 실패하면 그 뭔가 다른 코드를 또 추가해서 이 테스트가 실패할 수도 있고 그런 것들이 캐치 가능
    // 테스트 코드는 선택이 아니고 사실상 필수 -> 작성 방법을 알아야 함
}
