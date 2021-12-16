package com.kbs.core.beanfind;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.kbs.core.AppConfig;
import com.kbs.core.member.MemberService;
import com.kbs.core.member.MemberServiceImpl;

public class ApplicationContextBasicFindTest {

  AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

  @Test
  @DisplayName("빈 이름으로 조회")
  void findBeanByName() {

    MemberService memberService = ac.getBean("memberService", MemberService.class);
    assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
  }

  @Test
  @DisplayName("이름 없이 타입으로 조회")
  void findBeanByType() {

    MemberService memberService = ac.getBean(MemberService.class);
    assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
  }

  @Test
  @DisplayName("구체 타입으로 조회")
  void findBeanByName2() {
    //구체 타입으로 조회하면 변경시 유연성이 떨어짐
    MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
    assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
  }
  
  @Test
  @DisplayName("빈 이름으로 조회X")
  void findBeanByNameX() {

//    ac.getBean("XXXX", MemberServiceImpl.class);
    assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("XXXX", MemberServiceImpl.class));
  }
}
