package hello.core.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

//데이터베이스 확정이 안 됐기 떄문에 MemoryMemberRepository 생성
// -> 개발 진행 가능
// -> 메모리에서만 하기 때문에 테스트 용으로 사용
@Component
public class MemoryMemberRepository implements MemberRepository {
    public static Map<Long,Member> store = new HashMap<>();
    //실무에서는 concurrentHashMap을 사용해야함 -> 동시성 이슈가 있을 수 있기 때문에

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId); // findbyId하면 store 에서 get해서 넣으면 id로 찾는 거
    }
}
