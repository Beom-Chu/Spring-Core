package com.kbs.core.autowired;

import com.kbs.core.AutoAppConfig;
import com.kbs.core.discount.DiscountPolicy;
import com.kbs.core.member.Grade;
import com.kbs.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

public class AllBeanTest {

    @Test
    void findAllBean() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(DiscountService.class, AutoAppConfig.class);

        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);

        Assertions.assertThat(discountService).isInstanceOf(DiscountService.class);

        int discountPrice = discountService.discount(member, 20_000, "fixDiscountPolicy");
        Assertions.assertThat(discountPrice).isEqualTo(1_000);

        int discountPrice2 = discountService.discount(member, 20_000, "rateDiscountPolicy");
        Assertions.assertThat(discountPrice2).isEqualTo(2_000);
    }

    static class DiscountService {

        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policyList;

        /**
         Map<String, DiscountPolicy> : map의 키에 스프링 빈의 이름을 넣어주고, 그 값으로 DiscountPolicy 타입으로 조회한 모든 스프링 빈을 담아준다
         List<DiscountPolicy> : DiscountPolicy 타입으로 조회한 모든 스프링 빈을 담아준다
         */
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policyList) {
            this.policyMap = policyMap;
            this.policyList = policyList;

            System.out.println("[[[policyMap = " + policyMap);
            System.out.println("[[[policyList = " + policyList);
        }

        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);

            System.out.println("[[[discountCode = " + discountCode);
            System.out.println("[[[discountPolicy = " + discountPolicy);

            return discountPolicy.discount(member, price);
        }
    }
}
