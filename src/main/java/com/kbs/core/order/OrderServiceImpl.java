package com.kbs.core.order;

import com.kbs.core.annotation.MainDiscountPolicy;
import com.kbs.core.discount.DiscountPolicy;
import com.kbs.core.discount.FixDiscountPolicy;
import com.kbs.core.discount.RateDiscountPolicy;
import com.kbs.core.member.Member;
import com.kbs.core.member.MemberRepository;
import com.kbs.core.member.MemoryMemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

  //할인정책 변경 => 클라이언트 코드에 영향을 줌. OCP 위반.
//  private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//  private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
  
  private final MemberRepository memberRepository;
  private final DiscountPolicy discountPolicy;

  //어노테이션 사용 샘플
  public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
    this.memberRepository = memberRepository;
    this.discountPolicy = discountPolicy;
  }

  @Override
  public Order createOrder(Long memberId, String itemName, int itemPrice) {
    Member member = memberRepository.findById(memberId);
    int discountPrice = discountPolicy.discount(member, itemPrice);
    
    return new Order(memberId, itemName, itemPrice, discountPrice);
  }

  //테스트용도
  public MemberRepository getMemberRepository(){
    return memberRepository;
  }
}
