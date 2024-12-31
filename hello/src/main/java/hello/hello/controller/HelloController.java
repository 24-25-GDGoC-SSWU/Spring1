package hello.hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //스프링은 org 스프링 프레임워크에 있는 걸 임포트 해와야 함

public class HelloController {

    @GetMapping("hello") //웹 어플리케이션에서 /hello로 들어오면 아래의 메서드를 실행해줌
    public String hello(Model model) {
        model.addAttribute("data", "hello!!"); //MVC
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) { //외부에서 파라미터를 받음
        model.addAttribute("name", name);
        return "hello-template";
    }

    //문자를 내놓으라고 하는 경우
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // 내가 적은 문자 그대로 올라감(데이터를 그대로 내려줌)
    }

    //데이터를 내놓으라고 하는 경우
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name); // 파라미터로 받은 name 데이터
        return hello;
    }

    static class Hello { // 클래스 안에 또 클래스 생성 가능 HelloController.Hello 이렇게 사용 가능(자바에서 정식으로 지원하는 문법)
        private String name;

        public String getName() { //getter 꺼낼 때
            return name;
        }
        public void setName(String name) { //setter 넣을 때
            this.name = name;
        } }
}
