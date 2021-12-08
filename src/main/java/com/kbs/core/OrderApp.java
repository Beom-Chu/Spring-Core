package com.kbs.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.kbs.core.member.Grade;
import com.kbs.core.member.Member;
import com.kbs.core.member.MemberService;
import com.kbs.core.member.MemberServiceImpl;
import com.kbs.core.order.Order;
import com.kbs.core.order.OrderService;
import com.kbs.core.order.OrderServiceImpl;

public class OrderApp {

  public static void main(String[] args) {

  //순수 자바
//    MemberService memberService = new MemberServiceImpl();
//    OrderService orderService = new OrderServiceImpl();
    
  //역할과 구현을 분리
//    AppConfig appConfig = new AppConfig();
//    MemberService memberService = appConfig.memberService();
//    OrderService orderService = appConfig.orderService();
    
    //스프링 전환
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    MemberService memberService = ac.getBean("memberService", MemberService.class);
    OrderService orderService = ac.getBean("orderService", OrderService.class);

    Long memberId = 1L;
    Member member = new Member(memberId, "memberA", Grade.VIP);
    memberService.join(member);
    
    Order order = orderService.createOrder(memberId, "itemA", 20_000);
    
    System.out.println(order);
  }

}
