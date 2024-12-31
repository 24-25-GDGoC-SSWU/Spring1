package hello.hello.repository;

import hello.hello.domain.Member;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long, Member> store = new HashMap<>();//키:long, value:member
    private static long sequence = 0L; //sequence == 0,1,2 .. 이런 식으로 키 값을 생성해주는 것

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        System.out.println("Store contents after save: " + store); // 회원 저장 후 상태 확인
        return member; // 저장된 결과 반환
        //store에다가 넣기 전에 member에 id값을 세팅해주고 save하기 전에 이름은 넘어온 상태
        //name은 고객이 회원가입을 할 때 적는 이름
        //id는 시스템에 저장을 할 때 시스템이 정해주는 것
        //따라서 id를 세팅해준 다음에 store에 저장을 함
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //store에서 꺼내옴
        //store.get(id) 결과가 없을 경우 null이 반환될 수 있으므로 optional로 감싸서 반환
        // -> 이렇게 하면 클라이언트가 뭔가를 할 수 있음
    }

    @Override
    public Optional<Member> findByName(String name) {
        System.out.println("Store contents during findByName: " + store); // 이름으로 찾기 전 store 상태 출력
        return store.values().stream()
                .filter(member -> member.getName().equals(name)) //member.getName이 파리미터로 넘어온 name이랑 같은지 확인
                //-> 같은 경우에는 핉터링 되고 그 중에서 찾으면 그 값을 반환
                .findAny(); // 하나라도 찾는 것, 결과가 optional로 반환 -> 루프를 돌면서 같은 게 하나라도 있으면 그 값을 반환
        // 끝까지 돌았는데도 없으면 optional에 null에 null이 포함돼서 반환
    }

    @Override
    public List<Member> findAll() { // 루프 돌리기도 편해서 실무에서는 리스트 많이 사용
        return new ArrayList<>(store.values());//store에 있는 values 즉 member를 반환
    }

    public void clearStore() {
        store.clear(); //.clear() 하면 store를 싹 지움
    }
}
