package hello.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE) //ElementType.TYPE -> 필드에 붙을지 타입에 붙을지를 지정
//type은 클래스 레벨에 붙는 것
@Retention(RetentionPolicy.RUNTIME)
@Documented
//얘가 붙은 것은 컴포넌트 스캔에 추가할 거야!
public @interface MyIncludeComponent {
}