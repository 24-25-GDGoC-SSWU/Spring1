package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.springframework.context.annotation.ComponentScan.*;

@Configuration // 설정 정보니까
//빈을 끌어다가 스프링 빈으로 등록해야 됨
@ComponentScan (// 이렇게 하면 @component 애노테이션이 붙은 클래스를 다 찾아서 자동으로 스프링 빈으로 등록해줌
        basePackages = "hello.core", // 베이스 패키지 설정(탐색할 패키지의 시작위치 설정, hello.core 부터 하위패키지를 찾아 들어간다)
        //ex) hello.core.member -> 멤버 하위의 패키지를 찾아감 -> 멤버 패키지만 컴포넌트 스캔의 대상이됨(나머진 등록 x)
        basePackageClasses = AutoAppConfig.class, // 지정한 클래스의 패키지부터 찾음(hello.core; 부터 찾는다)
        //스프링 빈으로 등록하지 않고 뺄 걸 지정해줌
        excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        /*
        Configuration 이 애노테이션이 붙은 클래스는 다 밸 거다
        -> AppConfig는 수동으로 다 등록하는 건데 얘는 자동으로 등록되면 안됨 -> 충돌남
        */
)
public class AutoAppConfig {
    @Bean(name = "memoryMemberRepository") // 컴포넌트가 등록될 때 맨 앞글자는 소문자로 바뀌니까
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
