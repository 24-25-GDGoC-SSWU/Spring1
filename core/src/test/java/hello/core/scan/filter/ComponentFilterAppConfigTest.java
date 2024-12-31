package hello.core.scan.filter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.context.annotation.ComponentScan.Filter;

public class ComponentFilterAppConfigTest {
    @Test
    void filterScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = ac.getBean("beanA", BeanA.class);
        assertThat(beanA).isNotNull(); // includeFilters이기 때문에 null이면 안됨 -> 값이 조회가 되어야 함
        Assertions.assertThrows( //excludeFilters여서 bin name이 없는 게 맞음
                NoSuchBeanDefinitionException.class, // 해당 에러가 나와야 성공
                () -> ac.getBean("beanB", BeanB.class));
    }

    //나만의 컴포넌트를 스캔할 수 있는 기능
    @Configuration
    @ComponentScan(
            //FilterType.ANNOTATION -> 애노테이션과 관련된 필터를 만듦
            //ANNOTATION은 기본값이기 때문에 type = FilterType.ANNOTATION,를 생략해도 됨
            includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
    )
    static class ComponentFilterAppConfig {
    }
}
