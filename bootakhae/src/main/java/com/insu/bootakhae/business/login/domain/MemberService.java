package com.insu.bootakhae.business.login.domain;

import java.util.List;
import java.util.Optional;
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

  public Optional<MemberEntity> findByUserId(String userId) {
    return memberRepository.findByUserId(userId);
  }

  public MemberEntity findMemberEntityByLoginIdEquals(String loginId) {
    return memberRepository.findMemberEntityByUserIdEquals(loginId);
  }

  public boolean isExistMember(String userId) {
    Optional<MemberEntity> memberEntity = memberRepository.findByUserId(userId);
    return !memberEntity.equals(Optional.empty());
  }

}
