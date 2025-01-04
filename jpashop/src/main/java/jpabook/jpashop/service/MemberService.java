package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = true)// 모든 데이터 변경이나 로직들은 가급적이면 트랜잭션 안에서 실행되어야 함
//@AllArgsConstructor //lombok - 필드 모든 걸 가지고 생성자를 만들어주는 거임
@RequiredArgsConstructor // -> 더 나아감 // final이 있는 필드만 가지고 생성자를 만들어줌
public class MemberService {

    // 이제 얘를 변경할 일이 없기 때문에 final로 하는 것을 권장함
    // 컴파일 시점에 체크할 수 있기 때문에 final을 넣는 것을 추천함
    private final MemberRepository memberRepository;

/*    // setter injection
    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }*/

  /*  // 생성자 주입 -> 한 번 생성할 때 다 완성되기 때문에 중간에 멤버 리포지토리를 바꿀 일은 없음
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }*/


    /*
        회원 가입
        */
    @Transactional // 쓰기는 따로 설정함 <- 얘가 우선권을 가짐 (기본이 readOnly = false)
    public Long join(Member member) {

        validateDuplicateMemeber(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMemeber(Member member) {
        //EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 단건 조회
    public Member findOne(Long memberId) {
        return memberRepository.findOnde(memberId);
    }
}
