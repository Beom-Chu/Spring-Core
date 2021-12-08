package com.kbs.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.kbs.core.discount.DiscountPolicy;
import com.kbs.core.discount.RateDiscountPolicy;
import com.kbs.core.member.MemberRepository;
import com.kbs.core.member.MemberService;
import com.kbs.core.member.MemberServiceImpl;
import com.kbs.core.member.MemoryMemberRepository;
import com.kbs.core.order.OrderService;
import com.kbs.core.order.OrderServiceImpl;

/* 제어의 역전 IoC(Inversion of Control)
기존 프로그램은 클라이언트 구현 객체가 스스로 필요한 서버 구현 객체를 생성하고, 연결하고, 실행했다. 
한마디로 구현 객체가 프로그램의 제어 흐름을 스스로 조종했다. 개발자 입장에서는 자연스러운 흐름이다.
반면에 AppConfig가 등장한 이후에 구현 객체는 자신의 로직을 실행하는 역할만 담당한다.
프로그램의 제어 흐름은 이제 AppConfig가 가져간다. 
예를 들어서 OrderServiceImpl 은 필요한 인터페이스들을 호출하지만 어떤 구현 객체들이 실행될지 모른다. 
프로그램에 대한 제어 흐름에 대한 권한은 모두 AppConfig가 가지고 있다. 
심지어 OrderServiceImpl도 AppConfig가 생성한다. 
그리고 AppConfig는 OrderServiceImpl 이 아닌 OrderService 인터페이스의 다른 구현 객체를 생성하고 실행할 수 도 있다.
그런 사실도 모른체 OrderServiceImpl 은 묵묵히 자신의 로직을 실행할 뿐이다.
이렇듯 프로그램의 제어 흐름을 직접 제어하는 것이 아니라 외부에서 관리하는 것을 제어의 역전(IoC)이라 한다.
 */

/* IoC 컨테이너, DI 컨테이너
AppConfig 처럼 객체를 생성하고 관리하면서 의존관계를 연결해 주는 것을 IoC 컨테이너 또는 DI 컨테이너라 한다. 
의존관계 주입에 초점을 맞추어 최근에는 주로 DI 컨테이너라 한다.
또는 어샘블러, 오브젝트 팩토리 등으로 불리기도 한다.
 */

@Configuration
public class AppConfig {

  @Bean
  public MemberService memberService() {
    return new MemberServiceImpl(memberRepository());
  }

  @Bean
  public MemberRepository memberRepository() {
    return new MemoryMemberRepository();
  }

  @Bean
  public OrderService orderService() {
    return new OrderServiceImpl(memberRepository(), discountPolicy());
  }

  @Bean
  public DiscountPolicy discountPolicy() {
    /*
    AppConfig 에서 할인 정책 역할을 담당하는 구현을 FixDiscountPolicy RateDiscountPolicy 객체로 변경했다.
    이제 할인 정책을 변경해도, 애플리케이션의 구성 역할을 담당하는 AppConfig만 변경하면 된다. 
    클라이언트 코드인 OrderServiceImpl 를 포함해서 사용 영역의 어떤 코드도 변경할 필요가 없다.
    구성 영역은 당연히 변경된다. 구성 역할을 담당하는 AppConfig를 애플리케이션이라는 공연의 기획자로 생각하자.
    공연 기획자는 공연 참여자인 구현 객체들을 모두 알아야 한다.
     */
//    return new FixDiscountPolicy();
    return new RateDiscountPolicy();
  }
}
