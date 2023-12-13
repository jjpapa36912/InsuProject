package com.insu.bootakhae.business.login.domain;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

  MemberRepository memberRepository;

  @Autowired
  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  public void save(MemberEntity memberEntity) {
    memberRepository.save(memberEntity);
  }

  public List<MemberEntity> findAll() {
    return memberRepository.findAll();
  }

  public MemberEntity findMemberEntityByLoginIdEquals(String loginId) {
    return memberRepository.findMemberEntityByUserIdEquals(loginId);
  }

}
