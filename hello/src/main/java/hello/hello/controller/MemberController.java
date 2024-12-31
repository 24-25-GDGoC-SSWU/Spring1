package hello.hello.controller;

import hello.hello.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired //memberService를 스프링이 스프링 컨테이너에 있는 메머 서비스를 가져다가 연결을 딱 시켜줌
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 생성자에서 이렇게 쓰면 멤버 컨트롤러가 생성이 될 때 스프링 빈에 등록되어 있는 메머서비스 객체를 가져다가 딱 넣어줌
}