package com.kbs.core.autowired;

import com.kbs.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    /* 스프링 빈이 아닌 Member를 주입해서 테스트 */
    static class TestBean {

        /* 주입할 스프링 빈이 없어도 동작해야할 경우 required = false 옵션 사용 */
        @Autowired(required = false)
        public void setNoBean1(Member member) {
            System.out.println("[[[setNoBean1 = " + member);
        }

        /* @Nullable 사용시 자동 주입할 대상이 없으면 null이 입력됨 */
        @Autowired
        public void setNoBean2(@Nullable Member member){
            System.out.println("[[[setNoBean2 = " + member);
        }

        /* Optional<> 사용시 자동 주입할 대상이 없으면 Optional.empty 가 입력됨 */
        @Autowired
        public void setNoBean3(Optional<Member> member) {
            System.out.println("[[[setNoBean3 = " + member);
        }
    }
}
