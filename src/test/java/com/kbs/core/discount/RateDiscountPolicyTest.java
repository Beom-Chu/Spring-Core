package com.kbs.core.discount;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.kbs.core.member.Grade;
import com.kbs.core.member.Member;

class RateDiscountPolicyTest {

  DiscountPolicy discountPolicy = new RateDiscountPolicy();
  
  @Test
  @DisplayName("VIP는 10% 할인 적용")
  void vip_o() {
    //given
    Member member = new Member(1L, "memberVIP", Grade.VIP);
    
    //when
    int discount = discountPolicy.discount(member, 10_000);
    
    //then
    assertThat(discount).isEqualTo(1_000);
  }

  
  @Test
  @DisplayName("VIP가 아니면 10% 할인 미적용")
  void vip_x() {
    //given
    Member member = new Member(2L, "memberBASIC", Grade.BASIC);
    
    //when
    int discount = discountPolicy.discount(member, 10_000);
    
    //then
    assertThat(discount).isEqualTo(0);
  }
}
