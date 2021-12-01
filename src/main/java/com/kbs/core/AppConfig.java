package com.kbs.core;

import com.kbs.core.discount.RateDiscountPolicy;
import com.kbs.core.member.MemberService;
import com.kbs.core.member.MemberServiceImpl;
import com.kbs.core.member.MemoryMemberRepository;
import com.kbs.core.order.OrderService;
import com.kbs.core.order.OrderServiceImpl;

public class AppConfig {

  public MemberService memberService() {
    return new MemberServiceImpl(new MemoryMemberRepository());
  }

  public OrderService orderService() {
    return new OrderServiceImpl(new MemoryMemberRepository(), new RateDiscountPolicy());
  }
}
