package com.kbs.core.order;

import com.kbs.core.discount.DiscountPolicy;
import com.kbs.core.discount.FixDiscountPolicy;
import com.kbs.core.member.Member;
import com.kbs.core.member.MemberRepository;
import com.kbs.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

  private final MemberRepository memberRepository = new MemoryMemberRepository();
  private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
  
  @Override
  public Order createOrder(Long memberId, String itemName, int itemPrice) {
    Member member = memberRepository.findById(memberId);
    int discountPrice = discountPolicy.discount(member, itemPrice);
    
    return new Order(memberId, itemName, itemPrice, discountPrice);
  }

}
