package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
// Impl -> 구현체 하나만 있을 경우 그 인터페이스 명 뒤에 Impl이라고 많이 사용
public class MemberServiceImpl implements MemberService {
    //private final MemberRepository memberRepository = new MemoryMemberRepository(); //왼쪽은 인터페이스를 의존하지만 오른쪽은 실제 할당하는 부분 구현체를 의존
    //인터페이스만 가지고 있으면 null point exception이 날 수 있음
    // -> 호출을 해도 구현체가 없으니 터짐
    // -> 구현 객체를 선택해줘야 함

    //오로지 memberRepository라는 인터페이스만 가짐 (구체적인 것은 모름) -> DIP를 지킴
    private final MemberRepository memberRepository; // 스프링 빈이 아님 내가 직접 new해서 메모리 멤버 리포지토리를 넣은 것과 일치(스프링 컨테이너가 관리하지 않는 애)

    @Autowired // 의존 관계를 자동으로 주입
    // MemberRepository에 맞는 걸 찾아와서 의존관계를 자동으로 주입해주는 것
    // 컼ㅁ포넌트스캔을 쓰면 Autowired를 쓰게 됨
    // 왜냐? 컨포넌트스캔을 쓰면 내 빈이 자동으로 등록되는데 의존관를 설정할 수 있는 방법이 없으니까(수동으로 등록할 수 있는 장소가 없음)
    //마치 ac.getBean(MemberRepository.class) 하는 것과 유사
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        // join해서 save를 호출하면 다형성에 의해서 MemoryMemberRepository(인터페이스가 아니라)에 있는 save가 호출
        // 오버라이드한 게 호출
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트해보기 위한 코드 추가
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
