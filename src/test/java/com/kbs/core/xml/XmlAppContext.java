package com.kbs.core.xml;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import com.kbs.core.member.MemberService;
import com.kbs.core.member.MemberServiceImpl;
import com.kbs.core.order.OrderService;

public class XmlAppContext {

  @Test
  void XmlAppContextTest() {
    ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
    MemberService bean = ac.getBean("memberService", MemberService.class);
    assertThat(bean).isInstanceOf(MemberServiceImpl.class);
    
    OrderService bean2 = ac.getBean("orderService", OrderService.class);
    assertThat(bean2).isInstanceOf(OrderService.class);
  }
}
