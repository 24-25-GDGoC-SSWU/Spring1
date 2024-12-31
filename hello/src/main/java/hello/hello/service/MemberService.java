package hello.hello.service;

import hello.hello.domain.Member;
import hello.hello.repository.MemberRepository;
import hello.hello.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service
public class MemberService {
    //private final MemberRepository memberRepository = new MemoryMemberRepository(); //회원 서비스를 만들기 위한 회원 리포지토리 생성

    //MemberRepository를 내가 직접 new해서 생성하는 게 아니라 외부에서 넣어주게 바꿈 -> DI(dependency injection(의존성 주입))

    private final MemberRepository memberRepository;

//    //@Autowired //스프링이 생성을 할 때 스프링이 뜰 때 너는 멤버리포지토리가 필요하다는 것을 하고 딱 넣어줌
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원가입

    public Long join(Member member) {
        //같은 이름을 가진 회원은 중복 회원은 안됨
        validateDuplicateMember(member); //중복 회원 검증 memberRepository.save(member);
        return member.getId();
    }

    //findByName을 해서 뭔가 로직이 나옴 -> 로직이 나올 경우 메소드로 뽑는 게 좋음

    //중복 회원 검증
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()) // findByName을 해 그 결과는 optional Member니까 바로 optional.ifPresent해서 작동
                .ifPresent(m -> { // 멤버의 값이 있으면 아래의 명령을 수행해라
                    // optional이기에 가능한 코드(아니었으면 if null이면 이라는 조건을 하나 더 걸어야 했음) -> optional이기에 ifPresent 사용 가능
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });

        // optional을 바로 반환하는 것은 별로 좋지 않고 코드가 일단 안 이쁘기 때문에 위와 같은 방식으로 사용
    }

    //전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
