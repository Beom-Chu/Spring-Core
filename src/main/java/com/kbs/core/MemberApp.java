package com.kbs.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.kbs.core.member.Grade;
import com.kbs.core.member.Member;
import com.kbs.core.member.MemberService;
import com.kbs.core.member.MemberServiceImpl;

public class MemberApp {

  public static void main(String[] args) {
    
    //순수 자바
//  MemberService memberService = new MemberServiceImpl();
    
    //역할과 구현을 분리
//    AppConfig appConfig = new AppConfig();
//    MemberService memberService = appConfig.memberService();
    
    //스프링 전환
    /*ApplicationContext를 스프링 컨테이너라 함*/
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    MemberService memberService = ac.getBean("memberService", MemberService.class);
    
    Member member = new Member(1L, "memberA", Grade.VIP);
    
    memberService.join(member);
    
    Member findMember = memberService.findMember(1L);
    
    System.out.println("new member : " + member.getName());
    System.out.println("find member : " + findMember.getName());
  }
}
