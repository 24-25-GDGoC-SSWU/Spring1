package hello.hello;

import hello.hello.repository.MemberRepository;
import hello.hello.repository.MemoryMemberRepository;
import hello.hello.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 뜰 때 멤버서비스랑 멤버리포지토리를 둘다 스프링 빈에 등록하고 스프링 빈에 등록되어 있는 멤버리포지토리를 멤버서비스에 넣어줌
@Configuration
public class springConfig {
    @Bean // 스프링 빈을 내가 등록하겠다는 의미
    public MemberService memberService() {
        return new MemberService(memberRepository()); // 스프링 빈에 등록되어 있는 멤버 리포지토리를 멤버 서비스에 넣어줌
    }
    // 이렇게 해주면 스프링이 뜰 때 이 configration을 읽고 이거는 스프링 빈에 등록하라는 뜻이네하고 인식을 함
    // 그러면서 이 로직을(return new MemberService();)을 호출해서 스프링 빈에 등록해줌

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository(); //구현체 / 멤버리포지토리는 인터페이스임
    }
}
