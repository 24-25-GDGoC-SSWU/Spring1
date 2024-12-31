package hello.hello.repository;

import hello.hello.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); //java 8에 들어간 기능 -> findById나 findByName이 null값을 가질 수도 있음
                                        // -> null값을 그대로 반환하는 것보다 optional로 감싸서 반환
    Optional<Member> findByName(String name);
    // findBy를 사용해 id와 name을 가져올 수 있음
    List<Member> findAll(); //지금까지 저장된 모든 회원 정보 리스트를 반환
}
