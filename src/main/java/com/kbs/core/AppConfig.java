package com.kbs.core;

import com.kbs.core.discount.DiscountPolicy;
import com.kbs.core.discount.RateDiscountPolicy;
import com.kbs.core.member.MemberRepository;
import com.kbs.core.member.MemberService;
import com.kbs.core.member.MemberServiceImpl;
import com.kbs.core.member.MemoryMemberRepository;
import com.kbs.core.order.OrderService;
import com.kbs.core.order.OrderServiceImpl;

public class AppConfig {

  public MemberService memberService() {
    return new MemberServiceImpl(memberRepository());
  }

  private MemberRepository memberRepository() {
    return new MemoryMemberRepository();
  }

  public OrderService orderService() {
    return new OrderServiceImpl(memberRepository(), discountPolicy());
  }

  /*
  AppConfig 에서 할인 정책 역할을 담당하는 구현을 FixDiscountPolicy RateDiscountPolicy 객체로 변경했다.
  이제 할인 정책을 변경해도, 애플리케이션의 구성 역할을 담당하는 AppConfig만 변경하면 된다. 
  클라이언트 코드인 OrderServiceImpl 를 포함해서 사용 영역의 어떤 코드도 변경할 필요가 없다.
  구성 영역은 당연히 변경된다. 구성 역할을 담당하는 AppConfig를 애플리케이션이라는 공연의 기획자로 생각하자.
  공연 기획자는 공연 참여자인 구현 객체들을 모두 알아야 한다.
   */
  private DiscountPolicy discountPolicy() {
//    return new FixDiscountPolicy();
    return new RateDiscountPolicy();
  }
}
