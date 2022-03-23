package com.kbs.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


/** 프로토타입 빈의 특징
* 스프링 컨테이너에 요청할 때 마다 새로 생성된다.
* 스프링 컨테이너는 프로토타입 빈의 생성과 의존관계 주입 그리고 초기화까지만 관여한다.
* 종료 메서드가 호출되지 않는다.
* 그래서 프로토타입 빈은 프로토타입 빈을 조회한 클라이언트가 관리해야 한다. 종료 메서드에 대한 호출도
* 클라이언트가 직접 해야한다
*/
public class PrototypeTest {

    @Test
    void prototypeBeanFind() {

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        System.out.println("[[get bean1");
        PrototypeBean bean1 = ac.getBean(PrototypeBean.class);

        System.out.println("[[get bean2");
        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);

        System.out.println("[[[bean1 = " + bean1);
        System.out.println("[[[bean2 = " + bean2);

        Assertions.assertThat(bean1).isNotSameAs(bean2);

        ac.close();
    }

    @Scope("prototype")
    static class PrototypeBean {

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
