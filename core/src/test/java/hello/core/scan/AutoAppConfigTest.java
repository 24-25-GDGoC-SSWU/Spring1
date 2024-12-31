package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class AutoAppConfigTest {
    @Test
    void basicScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        //AutoAppConfig에는 아무것도 없고 컨포컨트스캔 애노테이션과 exclude 조건 하나만 존재

        //memberService 조회
        MemberService memberService = ac.getBean(MemberService.class);

        //검증
        assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
