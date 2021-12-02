package com.kbs.core;

import com.kbs.core.member.Grade;
import com.kbs.core.member.Member;
import com.kbs.core.member.MemberService;
import com.kbs.core.member.MemberServiceImpl;
import com.kbs.core.order.Order;
import com.kbs.core.order.OrderService;
import com.kbs.core.order.OrderServiceImpl;

public class OrderApp {

  public static void main(String[] args) {

//    MemberService memberService = new MemberServiceImpl();
//    OrderService orderService = new OrderServiceImpl();
    
    AppConfig appConfig = new AppConfig();
    MemberService memberService = appConfig.memberService();
    OrderService orderService = appConfig.orderService();

    Long memberId = 1L;
    Member member = new Member(memberId, "memberA", Grade.VIP);
    memberService.join(member);
    
    Order order = orderService.createOrder(memberId, "itemA", 10_000);
    
    System.out.println(order);
  }

}
