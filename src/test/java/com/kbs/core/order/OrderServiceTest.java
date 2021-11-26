package com.kbs.core.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import com.kbs.core.member.Grade;
import com.kbs.core.member.Member;
import com.kbs.core.member.MemberService;
import com.kbs.core.member.MemberServiceImpl;

class OrderServiceTest {

  MemberService memberService = new MemberServiceImpl();
  OrderService orderService = new OrderServiceImpl();
  
  @Test
  void testCreateOrder() {
    Long memberId = 1L;
    Member member = new Member(memberId, "memberA", Grade.VIP);
    memberService.join(member);
    
    Order order = orderService.createOrder(memberId, "itemA", 10000);
    
    Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
  }

}
